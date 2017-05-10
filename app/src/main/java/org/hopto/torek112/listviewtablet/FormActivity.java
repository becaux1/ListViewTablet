package org.hopto.torek112.listviewtablet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class FormActivity extends AppCompatActivity implements FormFragment.OnLibroInsertadoListener{

    protected Libro l;
    protected Intent i;
    private FormFragment ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_form);
        i=getIntent();//Recogemos intent

        //Vamos a utilizar el form tanto para editar como para insertar.
        //Por ello vamos a comprobar si el intent contiene datos, en caso de que contenga datos habra que cargarlos en el form.
        //En caso de que el intent venga vacio solo presentamos el form
        if(i.getExtras()!=null) rellenarForm();

        //Si no hay datos necesaitamos un form vacio

        /*
        Tenemos que instanciar el fragment y engancharlo al contenedor vacio para la insercion
         */
        ff=FormFragment.newInstance(null);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_form,ff).commit();
    }

    private void rellenarForm() {

        ((EditText)findViewById(R.id.et_titulo)).setText(i.getStringExtra(MainActivity.TITULO));
        ((EditText)findViewById(R.id.et_autor)).setText(i.getStringExtra(MainActivity.AUTOR));
        ((EditText)findViewById(R.id.et_resumen)).setText(i.getStringExtra(MainActivity.RESUMEN));

        RadioGroup rg=(RadioGroup)findViewById(R.id.rg_genero);

        switch(i.getIntExtra(MainActivity.GENERO, 1)){

            case 0:
                rg.check(R.id.rb_novela);
                break;
            case 1:
                rg.check(R.id.rb_poesia);
                break;
            case 2:
                rg.check(R.id.rb_teatro);
                break;

        }

        //((RadioGroup)findViewById(R.id.rg_genero)).check(i.getIntExtra(MainActivity.GENERO, 1));

    }

    public void onLibroInsertado(){

        /*
        Insercion de libro nuevo
        Recogemos los datos y los pasamos a la activiyMain por el intent
         */

        i.putExtra(MainActivity.TITULO, ((EditText)findViewById(R.id.et_titulo)).getText().toString());
        i.putExtra(MainActivity.AUTOR, ((EditText)findViewById(R.id.et_autor)).getText().toString());
        i.putExtra(MainActivity.RESUMEN, ((EditText)findViewById(R.id.et_resumen)).getText().toString());



        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void setGenero(int checkedId) {
        i.putExtra(MainActivity.GENERO,checkedId);
    }
}

package org.hopto.torek112.listviewtablet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResumenActivity extends AppCompatActivity {

    protected Libro l;
    protected Intent i;
    Bundle extras;
    private ResumenFragment rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_resumen);

        i=getIntent();//Recogemos intent
        extras = i.getExtras();

        //Vamos a utilizar el form tanto para editar como para insertar.
        //Por ello vamos a comprobar si el intent contiene datos, en caso de que contenga datos habra que cargarlos en el form.
        //En caso de que el intent venga vacio solo presentamos el form

        //rellenarForm();

        /*
        Instanciar el Fragment resumen con los datos del intent y enganchar el fragment al FrameLayout
         */

        rellenarLibro();

        rf=ResumenFragment.newInstance(l);
        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_resumen, rf).commit();



    }

    private void rellenarForm() {

        ((TextView)findViewById(R.id.tv_titulo_vacio)).setText(i.getStringExtra(MainActivity.TITULO));
        ((TextView)findViewById(R.id.tv_autor_vacio)).setText(i.getStringExtra(MainActivity.AUTOR));
        ((TextView)findViewById(R.id.tv_resumen_vacio)).setText(i.getStringExtra(MainActivity.RESUMEN));
        ((TextView)findViewById(R.id.tv_genero_vacio)).setText(i.getStringExtra(MainActivity.GENERO));

        //((RadioGroup)findViewById(R.id.rg_genero)).check(i.getIntExtra(MainActivity.GENERO, 1));

    }

    private void rellenarLibro(){
        /*

         */

        l= new Libro(extras.getString(MainActivity.TITULO), extras.getString(MainActivity.AUTOR));

        l.resumen= extras.getString(MainActivity.RESUMEN);
        int g=i.getIntExtra(MainActivity.GENERO,0);

        switch(g){
            case 0:
                l.genero=Libro.Genero.NOVELA;
                break;
            case 1:
                l.genero=Libro.Genero.POESIA;
                break;
            case 2:
                l.genero=Libro.Genero.TEATRO;
                break;
            default:
                break;

        }
    }

    public void onPulsame(View view){

        /*
        Insercion de libro nuevo
        Recogemos los datos y los pasamos a la activiyMain por el intent
         */

        setResult(RESULT_OK, i);
        finish();
    }
}

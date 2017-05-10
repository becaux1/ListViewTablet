package org.hopto.torek112.listviewtablet;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListaFragment.OnLibroSeleccionadoListener, ListaFragment.OnInsertarListener, FormFragment.OnLibroInsertadoListener{

    public static final int INSERT_CODE=0;
    public static final int EDIT_CODE=1;
    public static final int RESUMEN_CODE=2;

    public static String TITULO="titulo";
    public static String AUTOR="autor";
    public static String RESUMEN="resumen";
    public static String GENERO="genero";
    public static final String POSICION="posicion";

    public ListView lv;
    public ArrayList<Libro> modelo;
    public ArrayAdapter<Libro> adapter;
    public int pos=-1;
    Libro l;

    ListaFragment lf = null;
    NoSeleccionadoFragment nsf = null;
    ResumenFragment rf = null;
    FormFragment ff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        //Instancia fragment
        lf=new ListaFragment();
        this.l= new Libro("", "");

        //Carga fragment en el container
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.contenedor_lista, lf);
        if(grande()){
            //Se carga el layout_large
            nsf= new NoSeleccionadoFragment();
            transaction.add(R.id.contenedor_resumen,nsf);

        }else{
            //Se carga layout
        }

        transaction.commit();
    }

    public boolean grande(){

        return (findViewById(R.id.contenedor_resumen)!=null);
    }

    @Override
    public void OnLibroSeleccionado(Libro l) {
        //Comprobamos si estamos en pantalla grande o pantalla pequeña

        if(grande()){

            //Intsnaciamos el fragment pasandole el libro
            rf=ResumenFragment.newInstance(l);
            //Cargamos Fragment e contenedor
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor_resumen,rf);
            transaction.commit();
        } else {
            Intent i = new Intent(this, ResumenActivity.class);

            i.putExtra(AUTOR, l.autor);
            i.putExtra(TITULO, l.titulo);
            i.putExtra(RESUMEN, l.resumen);
            i.putExtra(GENERO, l.genero.ordinal());
            startActivityForResult(i, RESUMEN_CODE);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        /*
        Recogemos los datos del nuevo libro insertado y los insertamos en la lista
         */

        /*
        Hay que comprobar si volvemos de una insercion o una edicion, para esto usamos requestCode
         */

        if(requestCode!=RESUMEN_CODE){

            Bundle b= intent.getExtras();
            l=new Libro(b.getString(TITULO), b.getString(AUTOR),b.getString(RESUMEN), Libro.Genero.POESIA);

            switch(b.getInt(GENERO)){
                case R.id.rb_novela:
                    l.genero= Libro.Genero.NOVELA;
                    break;
                case R.id.rb_poesia:
                    l.genero= Libro.Genero.POESIA;
                    break;
                case R.id.rb_teatro:
                    l.genero= Libro.Genero.TEATRO;
                    break;

            }
            /*
            Las manipulaciones en el adapter se realizan en el fragment que es quien aloja el adapter, y por tanto en los metodos de callBack de elementos de fragment
            (onLibroInsertado, etc)
             */
            /*
            if(requestCode==EDIT_CODE){
                //editar
                adapter.remove(modelo.get(pos));
                adapter.insert(l, pos);
            }
            else{
                //insertar
                adapter.add(l);
            }
            */
            lf.aa.add(l);
        }

    }

    @Override
    public long onInsertar() {
        if(grande()){
            //cargar FormFragment a la derecha vacío en el contendor ff
            ff = FormFragment.newInstance(null);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor_resumen, ff);
            transaction.commit();

        }else{
            Intent i = new Intent(this, FormActivity.class);
            startActivityForResult(i, INSERT_CODE);
        }
        return 0;

    }

    @Override
    public void onLibroInsertado() {

        if(grande()){
            l.titulo=((EditText)findViewById(R.id.et_titulo)).getText().toString();
            l.autor=((EditText)findViewById(R.id.et_autor)).getText().toString();
            l.resumen=((EditText)findViewById(R.id.et_resumen)).getText().toString();
            l.genero=Libro.Genero.POESIA;
            lf.aa.add(l);
            ((EditText)findViewById(R.id.et_titulo)).setText("");
            ((EditText)findViewById(R.id.et_autor)).setText("");
            ((EditText)findViewById(R.id.et_resumen)).setText("");

        }else{

            /*
            El libro se habra actualizado en el metodo activityResult recuperando los datos del INTENT
             */
            lf.aa.add(l);
        }
    }

    @Override
    public void setGenero(int checkedId) {
        switch(checkedId){
            case R.id.rb_novela:
                l.genero= Libro.Genero.NOVELA;
                break;
            case R.id.rb_poesia:
                l.genero= Libro.Genero.POESIA;
                break;
            case R.id.rb_teatro:
                l.genero= Libro.Genero.TEATRO;
                break;

        }
    }
}


/*
Implementar un evento en un widget Fragment

    1.- En la clase del Fragment, metodo onCreateView, despues de inflar el layout y gestionar ese widget (findViewById)
    , se le pone el listener con el evento original onClickListener) y mediante una clase anonima se redirige ese elemento
    hacia otro de una interfaz definida en el propio Fragment
    2.- En la clase del Fragment definimos una interfaz para cada evento que se vaya a escuchar con un solo metodo sin implementar
    3.- En la clase del Fragment en el metodo onAtach(), se agrega como atributo cada uno de los listener
    4.-En la clase del fragment en el metodo onAttach se rellena el listener correspondiente con la actividad donde se va a enganchar
    el fragment haciendo un CAST con la interfaz de ese evento. Si la actividad no implementa esa interfaz, se rechaza el attach
    5.- La clase de la actividad principal debe implementar todas las interfaces de sus fragments y por tanto todos los metodos que llevan
 */
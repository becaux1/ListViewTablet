package org.hopto.torek112.listviewtablet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by alguien on 27/01/2017.
 */

public class ListaFragment extends Fragment {

    /*Interfaces*/
    public interface OnLibroSeleccionadoListener{

        public void OnLibroSeleccionado(Libro l);

    }

    public interface InsertaLibro{

        public void InsertaLibro(Libro l);

    }

    /*Atributos*/
    protected ListView lv;
    protected MiAdapter aa;
    protected ArrayList<Libro> modelo;


    private OnLibroSeleccionadoListener listener_seleccion=null;
    private OnInsertarListener listener_insercion = null;

    /*
    Creamos interfaces que obliguen a la actividad a implementar ciertos metodos en respuesta a eventos

    El fragment solo se enganchara a una actividad si implementa las interfaces adecuadas.
    */

    public interface OnInsertarListener{
        //el onclick del boton debe redirirgir hacia esste metodo
        public long onInsertar();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Generar los datos

        modelo= new ArrayList<Libro>();
        modelo.add(new Libro("El quijote", "Cervantes","libro", Libro.Genero.POESIA));
        modelo.add(new Libro("Mi Vida", "Hector", "libro", Libro.Genero.NOVELA));
        modelo.add(new Libro("La vida de naufrago", "David", "Zarauzo", Libro.Genero.TEATRO));
        modelo.add(new Libro("La vida de David", "David", "Un poco calvo", Libro.Genero.TEATRO));
        modelo.add(new Libro("Mi Vida", "Hector", "libro", Libro.Genero.NOVELA));

        //Inflar el layout del fragment con la referencia al contenedor de alguna actividad
        View result = inflater.inflate(R.layout.fragment_lista, container, false);

        //Rellenar el ListView utilizando el adapter

        aa= new MiAdapter(getActivity(), modelo);
        lv=(ListView) result.findViewById(R.id.fragmentList);
        lv.setAdapter(aa);

        /*
        Colocar el listener de clicks
         */

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(listener_seleccion!=null){
                            Libro l= aa.getItem(position);
                            listener_seleccion.OnLibroSeleccionado(l);
                        }
                    }
                }
        );



        /*Gestión del onclick sobre el botón*/
        Button b = (Button) result.findViewById(R.id.b_insertar_nuevo); //debemos poner el boton de la ista
        b.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //redirigir hacia e metodo de la interfaz
                        ((MainActivity)getActivity()).onInsertar();
                    }
                }
        );
        return result;
    }

    @Override
    public void onAttach(Context activity){

        super.onAttach(activity);

        /*
        Comprobamos que el contexto implementa los metodos necesarios con un CAST
         */

        try{
            listener_seleccion=(OnLibroSeleccionadoListener)activity;
            listener_insercion=(OnInsertarListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+"Debe implementar OnLibroSeleccionadoListener");
        }

    }

    public void onDettach(){

        super.onDetach();

        /*
        Ponemos los listener a null
         */
        listener_seleccion=null;
        listener_insercion=null;

    }

    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);

    }
}

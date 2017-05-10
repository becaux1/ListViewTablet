package org.hopto.torek112.listviewtablet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alguien on 27/01/2017.
 */

public class ResumenFragment extends Fragment {


    TextView tv_titulo, tv_autor, tv_resumen, tv_genero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Generar los datos

        View result = inflater.inflate(R.layout.fragment_resumen, container, false);
        Bundle args = getArguments();
        if(args!=null){


            tv_titulo=(TextView) result.findViewById(R.id.tv_titulo_vacio);
            tv_titulo.setText(args.getString(MainActivity.TITULO));

            tv_autor=(TextView) result.findViewById(R.id.tv_autor_vacio);
            tv_autor.setText(args.getString(MainActivity.AUTOR));

            tv_resumen=(TextView) result.findViewById(R.id.tv_resumen_vacio);
            tv_resumen.setText(args.getString(MainActivity.RESUMEN));

            tv_genero=(TextView) result.findViewById(R.id.tv_genero_vacio);
            tv_genero.setText(args.getString(MainActivity.GENERO));

        }


        
        return result;
    }

    @Override
    public void onAttach(Context activity){

        super.onAttach(activity);

        //asi se queda

    }

    public void onDettach(){

        super.onDetach();

        //asi se queda

    }

    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);

    }

    public static ResumenFragment newInstance(Libro l){

        ResumenFragment fragment = new ResumenFragment();
        Bundle args= new Bundle();
        args.putString(MainActivity.TITULO, l.titulo);
        args.putString(MainActivity.AUTOR, l.autor);
        args.putString(MainActivity.RESUMEN, l.resumen);
        args.putString(MainActivity.GENERO, l.genero.toString());

        fragment.setArguments(args);

        return fragment;
    }
}

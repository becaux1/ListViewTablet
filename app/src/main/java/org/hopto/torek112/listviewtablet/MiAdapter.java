package org.hopto.torek112.listviewtablet;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alguien on 13/01/2017.
 */

public class MiAdapter extends ArrayAdapter<Libro>{

    static class ViewHolder{

        protected TextView tv_titulo;
        protected TextView tv_autor;
        protected LinearLayout ll;

    }

    private Activity ctx= null;
    private ArrayList<Libro> modelo;

    public MiAdapter(Activity ctx, ArrayList<Libro> modelo){

        super(ctx, R.layout.layout_row, modelo);
        this.ctx=ctx;
        this.modelo=modelo;
        notifyDataSetChanged();

    }

    public View getView(int position, View convertView, ViewGroup parent){

        View row= convertView;

        if(row==null){
            /*
            el ListView aun no esta lleno, es decir, faltan filas por instanciar
            En este caso
             */

            row=ctx.getLayoutInflater().inflate(R.layout.layout_row, null);
            ViewHolder vh=new ViewHolder();
            vh.tv_titulo=(TextView)row.findViewById(R.id.row_titulo);
            vh.tv_autor=(TextView)row.findViewById(R.id.row_autor);
            vh.ll=(LinearLayout)row.findViewById(R.id.row_layout);

            /*
            Al inflar una fila nueva el holder guarda los id y no tendremos que hacer nuevos findViewById

             */

            row.setTag(vh);
        }

        ViewHolder vh=(ViewHolder)row.getTag();

            //Buscamos el libro en el modelo por su posicion

        Libro l=modelo.get(position);
        vh.tv_titulo.setText(l.titulo);
        vh.tv_autor.setText(l.autor);

        if(l.genero.equals(Libro.Genero.NOVELA))
            vh.ll.setBackgroundColor(Color.CYAN);
        else if(l.genero.equals(Libro.Genero.TEATRO))
            vh.ll.setBackgroundColor(Color.RED);
        else if(l.genero.equals(Libro.Genero.POESIA))
            vh.ll.setBackgroundColor(Color.BLUE);
        return row;

    }

}

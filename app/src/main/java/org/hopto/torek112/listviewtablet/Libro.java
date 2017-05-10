package org.hopto.torek112.listviewtablet;

/**
 * Created by alguien on 12/16/2016.
 */

public class Libro {

    protected enum Genero{POESIA, NOVELA, TEATRO};
    protected Genero genero;
    protected String autor, titulo, resumen;


    public Libro(String aut, String tit){

        this.autor=aut;
        this.titulo=tit;
    }

    public Libro(String tit, String aut, String res, Genero gen){

        this.autor=aut;
        this.titulo=tit;
        this.resumen=res;
        this.genero=gen;
    }

    public String toString(){

        return titulo+", "+autor;
    }
}

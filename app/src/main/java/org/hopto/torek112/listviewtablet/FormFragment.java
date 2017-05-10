package org.hopto.torek112.listviewtablet;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by alguien on 27/01/2017.
 */

public class FormFragment extends Fragment {


    protected static Libro l;
    static Bundle args;

    public interface OnLibroInsertadoListener{
        public void onLibroInsertado();
        public void setGenero(int checkedId);
    }

    protected OnLibroInsertadoListener listener_insertado=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Generar los datos

        View result = inflater.inflate(R.layout.fragment_form, container, false);
        //args = getArguments();

        /*
        Si estamos insertando hay que recoger los datos del form y ponerlos en el libro
         */

        /*
        Poner el listener de clicks en el boton y redireccionarlo al metodo de la interfaz en main
         */
        l= new Libro(
                ((EditText)result.findViewById(R.id.et_titulo)).getText().toString(),
                ((EditText)result.findViewById(R.id.et_autor)).getText().toString()
        );
        l.resumen=((EditText)result.findViewById(R.id.et_resumen)).getText().toString();

        /*

        De momento el radiogroup no se toca
         */

        Button b=(Button)result.findViewById(R.id.b_insertar);

        b.setOnClickListener(
              new View.OnClickListener(){
                  public void onClick(View v){
                      listener_insertado.onLibroInsertado();
                  }

              }
        );

        RadioGroup rg= (RadioGroup)result.findViewById(R.id.rg_genero);
        rg.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        listener_insertado.setGenero(checkedId);
                    }
                }
        );

        return result;
    }

    @Override
    public void onAttach(Context activity){

        super.onAttach(activity);

        /*
        Este fragment solo se engancha a una activity que implemente la inetrfaz OnLibroSeleccionadoListener
         */
        try{
            listener_insertado=(OnLibroInsertadoListener)activity;
        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"Debe implementar la interfaz");
        }


    }

    public void onDettach(){

        super.onDetach();

        //asi se queda
        listener_insertado=null;

    }



    public void onActivityCreated(Bundle savedInstanceState){

        super.onActivityCreated(savedInstanceState);

    }

    public static FormFragment newInstance(Libro libro){

        FormFragment fragment = new FormFragment();
        l = libro;


        fragment.setArguments(args);

        return fragment;
    }
}

package org.hopto.torek112.listviewtablet;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by alguien on 27/01/2017.
 */

public class NoSeleccionadoFragment extends Fragment {

    TextView tv_titulo, tv_autor, tv_resumen, tv_genero;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //Generar los datos
        View result = inflater.inflate(R.layout.fragment_noseleccionado, container, false);
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
}

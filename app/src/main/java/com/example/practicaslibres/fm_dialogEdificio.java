package com.example.practicaslibres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class fm_dialogEdificio extends DialogFragment {

    private static final String TAG ="dialogListaEdificio"; //nombre de fragment

    //objetos
    private EditText edt1, edt2;
    public TextView tvOk, tvCancel;
    public Button btn_guardar,btn_salir,btn_eliminar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_edificos, container, false);

        //objetos
        btn_guardar = view.findViewById(R.id.btnEdificio_guardar);
        btn_salir = view.findViewById(R.id.btnEdificio_salir);
        btn_eliminar = view.findViewById(R.id.btnEdificio_Eliminar);

        //Para cancelar
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();
            }
        });


        //para confirmar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Guardando...");
                String input = edt1.getText().toString();
                if(!input.equals("")){
                    //aca toda la logica

                }
            }
        });
        //para eliminar
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Eliminando...");
                String input = edt1.getText().toString();
                if(!input.equals("")){
                    //aca toda la logica

                }
            }
        });

        return view;
    }
}

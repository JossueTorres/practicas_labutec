package com.example.practicaslibres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class fm_en_dialogListaPracticas extends DialogFragment {

    private static final String TAG ="dialogListaPrac"; //nombre de fragment

    //objetos
    private EditText edt1, edt2;
    public TextView tvOk, tvCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dialog_lista_practicas, container, false);

        //objetos
        edt1 = view.findViewById(R.id.edt1_dialogListPrac);
        tvOk = view.findViewById(R.id.tvOK_dialog_listPrac);
        tvCancel = view.findViewById(R.id.tvCancel_dialogListPrac);

        //Para cancelar
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();
            }
        });


        //para confirmar
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "ok...");
                String input = edt1.getText().toString();
                if(!input.equals("")){
                    //aca toda la logica

                }
            }
        });

        return view;
    }
}

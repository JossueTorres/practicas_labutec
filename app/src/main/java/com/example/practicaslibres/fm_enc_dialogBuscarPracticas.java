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

public class fm_enc_dialogBuscarPracticas extends DialogFragment {


    private static final String TAG ="dialogBuscarPrac"; //nombre de fragment
    //objetos
    EditText edtDesde, edtHasta;
    Button btnBuscar, btnCancelar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dialog_enc_buscar_practicas, container, false);


        btnBuscar = view.findViewById(R.id.btnBuscar_encBuscarPrac);


        //buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Buscando...");
            }
        });



        return view;
    }
}

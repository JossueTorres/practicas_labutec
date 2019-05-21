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

public class fm_enc_dialogNuevoPracticas extends DialogFragment {

    private static final String TAG ="dialogNuevoPrac"; //nombre de fragment

    Button btnAgregar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dialog_enc_nuevo_practicas, container, false);

        btnAgregar = view.findViewById(R.id.btnGuardar_encNuevoPrac);

        //guardar
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Guardando...");
            }
        });
        return view;
    }
}

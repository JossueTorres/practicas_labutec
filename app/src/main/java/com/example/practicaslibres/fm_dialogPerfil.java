package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class fm_dialogPerfil extends DialogFragment {

    private static final String TAG ="dialogPerfil"; //nombre de fragment

    //objetos
    private EditText edt_nombre,edt_claveA,edt_claveN;
    public TextView tvOk, tvCancel;
    public FloatingActionButton btn_guardar,btn_salir;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_perfil, container, false);

        //objetos
        btn_guardar = view.findViewById(R.id.btn_permod_guardar);
        btn_salir = view.findViewById(R.id.btn_permod_salir);

        edt_nombre = view.findViewById(R.id.edt_per_nombre);
        edt_claveA = view.findViewById(R.id.edt_per_clave1);
        edt_claveN = view.findViewById(R.id.edt_per_clave2);

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
                getDialog().dismiss();
            }
        });

        return view;
    }

    }









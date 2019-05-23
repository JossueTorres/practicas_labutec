package com.example.practicaslibres;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class fm_enc_listaPracticas extends Fragment {

    //definicion de objetos y nombre a fragment(tag)
    private static final String TAG="fragmentListaPrac";
    private Button btnOpenDialog, btnBuscar, btnNuevo;
    public TextView tvReciboDato;

    //metodo creado
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fm_enc_lista_practicas, container, false);

        //objetos

        btnBuscar = view.findViewById(R.id.btnBuscar_encListaPrac);
        btnNuevo = view.findViewById(R.id.btnNuevo_encListaPrac);
        tvReciboDato = view.findViewById(R.id.tvReciboDatos_encListaPrac);

        //Dialog para nuevo
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // log
                Log.d(TAG,"Mostrar Nuevo"); //log de fragment

                //dialog a invocar
                fm_enc_dialogNuevoPracticas dialog = new fm_enc_dialogNuevoPracticas();
                dialog.show(getFragmentManager(), "dialogNuevoPrac");
            }
        });

        //dialog para buscar
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // log
                Log.d(TAG,"Mostrar Buscar"); //log de fragment

                //dialog a invocar
                fm_enc_dialogBuscarPracticas dialog = new fm_enc_dialogBuscarPracticas();
                dialog.show(getFragmentManager(), "dialogBuscarPrac");
            }
        });

        return view;
    }









}

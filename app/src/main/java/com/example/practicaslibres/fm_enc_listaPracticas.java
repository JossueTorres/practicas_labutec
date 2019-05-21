package com.example.practicaslibres;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class fm_enc_listaPracticas extends Fragment {

    //definicion de objetos y nombre a fragment(tag)
    private static final String TAG="fragmentListaPrac";
    private Button btnOpenDialog;

    //metodo creado
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fm_enc_lista_practicas, container, false);

        //objetos
        btnOpenDialog = view.findViewById(R.id.btnDialog_listaPracticas);

        btnOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"mensaje"); //log de fragment

                //dialog a invocar
                fm_en_dialogListaPracticas dialog = new fm_en_dialogListaPracticas();
                dialog.show(getFragmentManager(), "dialogListaPrac");
            }
        });

        return view;
    }









}

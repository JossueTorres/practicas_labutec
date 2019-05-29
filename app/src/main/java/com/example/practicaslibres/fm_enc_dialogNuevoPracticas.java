package com.example.practicaslibres;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class fm_enc_dialogNuevoPracticas extends DialogFragment {

    private static final String TAG ="dialogNuevoPrac"; //nombre de fragment

    public FloatingActionButton btn_guardar,btn_salir;

    EditText edtDesde, edtHasta, edtHoraInicio, edtHoraFin;
    CheckBox chkLu, chkMa, chkMi, chkJu, chkVi, chkSa, chkDo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dialog_enc_nuevo_practicas, container, false);

        btn_guardar = view.findViewById(R.id.btnGuardar_encNuevoPrac);
        edtDesde = view.findViewById(R.id.edtDesde_encNuevoPrac);
        edtHasta = view.findViewById(R.id.edtHasta_encNuevoPrac);
        edtHoraInicio = view.findViewById(R.id.edtHoraI_encNuevoPrac);
        edtHoraFin = view.findViewById(R.id.edtHoraF_encNuevoPrac);
        chkDo =view.findViewById(R.id.chkDo_encNuevoPrac);
        chkLu =view.findViewById(R.id.chkLu_encNuevoPrac);
        chkMa =view.findViewById(R.id.chkMa_encNuevoPrac);
        chkMi =view.findViewById(R.id.chkMi_encNuevoPrac);
        chkJu =view.findViewById(R.id.chkJu_encNuevoPrac);
        chkVi =view.findViewById(R.id.chkVi_encNuevoPrac);
        chkSa =view.findViewById(R.id.chkSa_encNuevoPrac);


        //guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Guardando...");

                String valorEntrada = edtDesde.getText().toString();

                //aca validaciones de cajas, pueden usar las utls
                if(!valorEntrada.equals("")){


                }
            }
        });
        return view;
    }
}

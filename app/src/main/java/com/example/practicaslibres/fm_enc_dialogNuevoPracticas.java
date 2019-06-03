package com.example.practicaslibres;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class fm_enc_dialogNuevoPracticas extends DialogFragment {

    private static final String TAG ="dialogNuevoPrac"; //nombre de fragment


    public FloatingActionButton btn_guardar,btn_salir;

    Button btnDesde,btnHasta,btnHoraInicio,btnHoraFin;
    EditText edtDesde, edtHasta, edtHoraInicio, edtHoraFin;
    CheckBox chkLu, chkMa, chkMi, chkJu, chkVi, chkSa, chkDo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.dialog_enc_nuevo_practicas, container, false);

        btnDesde=view.findViewById(R.id.btnDesdeNuevaPrac);
        btnHasta=view.findViewById(R.id.btnHastaNuevaPrac);
        btnHoraInicio=view.findViewById(R.id.btnHoraInicioNuevaPrac);
        btnHoraFin=view.findViewById(R.id.btnHoraFinNuevaPrac);


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


        //desde
        btnDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.fecha(getContext(),edtDesde);
            }
        });

        btnHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.fecha(getContext(),edtHasta);
            }
        });

        btnHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.hora(getContext(),edtHoraInicio);
            }
        });

        btnHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.hora(getContext(),edtHoraFin);
            }
        });

        //guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtDesde.getText().toString().equals("")){
                    edtDesde.setError("Campo requerido");
                    edtDesde.requestFocus();
                    return;
                }
                if (edtHasta.getText().toString().equals("")){
                    edtHasta.setError("Campo requerido");
                    edtHasta.requestFocus();
                    return;
                }
                if (edtHoraInicio.getText().toString().equals("")){
                    edtHoraInicio.setError("Campo requerido");
                    edtHoraInicio.requestFocus();
                    return;
                }
                if (edtHoraFin.getText().toString().equals("")){
                    edtHoraFin.setError("Campo requerido");
                    edtHoraFin.requestFocus();
                    return;
                }
                if (!hayUnDiaSeleccionado()){
                    Toast.makeText(getContext(),"TIENE QUE SELECCIONAR UN DIA\n" +
                            "COMO MINIMO",Toast.LENGTH_LONG).show();
                    return;
                }


                try {
                    if (!metodos.validarFechaDesde(edtDesde)){
                        edtDesde.setError("La fecha ya no es valida");
                        edtDesde.requestFocus();
                        //Toast.makeText(getContext(),"LA FECHA \"DESDE\" NO PUEDE SER ANTERIOR A LA DE ESTE DIA",Toast.LENGTH_LONG).show();
                        return;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    if (!metodos.validarFechaHasta(edtDesde,edtHasta)){
                        edtHasta.setError("Esta fecha debe ser mayor o igual que la anterior");
                        edtHasta.requestFocus();
                        //Toast.makeText(getContext(),"LA FECHA \"HASTA\" NO PUEDE SER ANTERIOR A LA DE ESTE DIA",Toast.LENGTH_LONG).show();
                        return;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Guardando...");

                String valorEntrada = edtDesde.getText().toString();

                //aca validaciones de cajas, pueden usar las utls
                if(!valorEntrada.equals("")){

                }
            }
        });
        return view;
    }

    public boolean hayUnDiaSeleccionado(){
        if (!chkLu.isChecked() && !chkMa.isChecked() && !chkMi.isChecked() && !chkJu.isChecked()
        && !chkVi.isChecked() && !chkSa.isChecked() && !chkDo.isChecked()){
            return false;
        }
        return true;
    }

}

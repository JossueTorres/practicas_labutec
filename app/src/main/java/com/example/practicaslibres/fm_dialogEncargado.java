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

public class fm_dialogEncargado extends DialogFragment {

    private static final String TAG ="dialogListaEdificio"; //nombre de fragment

    //objetos
    private EditText edtNombre, edtCorreo,edtClave1,edtClave2;
    public TextView tvOk, tvCancel;
    public FloatingActionButton btn_guardar,btn_salir,btn_eliminar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_encargados, container, false);

        //objetos
        btn_guardar = view.findViewById(R.id.btnEnc_guardar);
        btn_salir = view.findViewById(R.id.btnEnc_salir);
        btn_eliminar = view.findViewById(R.id.btnEnc_Eliminar);

        edtNombre = view.findViewById(R.id.edt_enc_nombre);
        edtCorreo = view.findViewById(R.id.edt_enc_correo);
        edtClave1 = view.findViewById(R.id.edt_enc_clave1);
        edtClave2 = view.findViewById(R.id.edt_enc_clave2);

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
                registrarEdificio_ws();
            }
        });
        //para eliminar
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Eliminando...");

            }
        });

        return view;
    }

    //--- Metodo para consumir servicios por volley---

    private void registrarEdificio_ws(){

        //Cargando, barra de progreso...
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Por favor espere...", "Registrando Edificio",
                false, false);

        //Url de Servicio
        String POST_URL = "http://104.248.185.225/practicaslab_utec/apis/admin/Edificio_api/insertEdificio";

        //Solicitud de cadena
        StringRequest stringRequest = new StringRequest(Request.Method.POST, POST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();


            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    loading.dismiss();
                    Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

                    }
            })
        {
            @Override
            protected Map<String, String> getParams() {

                //Parametros de ws
                Map<String, String> params = new HashMap<String, String>();
                params.put("txtNombre", edtNombre.getText().toString());
                params.put("txtCorreo", edtCorreo.getText().toString());
                params.put("txtClave", edtClave1.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }







}

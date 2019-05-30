package com.example.practicaslibres;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class fm_dialogEdificio extends DialogFragment {

    private static final String TAG ="dialogListaEdificio"; //nombre de fragment

    //objetos
    private EditText edtNombre, edtAcronimo;
    public TextView tvOk, tvCancel;
    public FloatingActionButton btn_guardar,btn_salir,btn_eliminar;

    String codigo="", nombre="", acronimo="", estado="", urlPost="";
    int progreso=0;

    String getCode="", getNombre="", getAcronimo="", getEstado="";
    @SuppressLint("RestrictedApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_edificos, container, false);

        //objetos
        btn_guardar = view.findViewById(R.id.btnEdf_guardar);
        btn_salir = view.findViewById(R.id.btnEdf_salir);
        btn_eliminar = view.findViewById(R.id.btnEdf_Eliminar);

        edtNombre = view.findViewById(R.id.edt_edf_nombre);
        edtAcronimo = view.findViewById(R.id.edt_edf_acronimo);

        //ver de que proceso viene
        Bundle bundle = getArguments();
        String proceso = bundle.getString("proceso", "insert");

        edtNombre.setText(getCode);

            if(proceso=="insert")
                btn_eliminar.setVisibility(View.GONE);
            else if(proceso=="delete")
                btn_guardar.setVisibility(View.GONE);


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

                invocarServicioRegistrar ws = new invocarServicioRegistrar();
                ws.execute();
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




    //SUBCLASE  ::::::::::::::::::::::::::::::::::::::::::::::::::::

    //subclase para ejecutar en segundo plano la peticion ws
    private class invocarServicioRegistrar extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            //tota la logica
            //servicio

            codigo="0";
            estado="A";
            nombre = edtNombre.getText().toString();
            acronimo = edtAcronimo.getText().toString();

            urlPost = "http://104.248.185.225/practicaslab_utec/Edificio/guardarDatos";
            registrarServicio(codigo, nombre, acronimo, estado, urlPost);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            btn_guardar.setClickable(true);
            Toast.makeText(getContext(), "Edificio registrado exitosamente", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {
            progreso=0;
            btn_guardar.setClickable(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    //WEB SERVICES  ::::::::::::::::::::::::::::::::::::::::::::::::::::

    //web service
    private void registrarServicio(String codigo, String nombre, String acronimo, String estado, String pUrl){

        HashMap<String, String> parametros = new HashMap<String, String>();
        parametros.put("cod", codigo);
        parametros.put("nom", nombre);
        parametros.put("acr", acronimo);
        parametros.put("est", estado);

        String response = "";
        try {

            URL url = new URL(pUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //establecer tiempos de respuestas
            con.setReadTimeout(15000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametros));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = con.getResponseCode();
            if(responseCode ==HttpURLConnection.HTTP_OK){
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                while ((line=br.readLine())!=null){
                    response +=line;
                }
            }else {
                response="";
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    //metodo para tratar la cadena que se obtiene
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()) {

            if(first) first=false;
            else result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }







}

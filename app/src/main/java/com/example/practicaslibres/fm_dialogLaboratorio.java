package com.example.practicaslibres;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class fm_dialogLaboratorio extends DialogFragment {

    private static final String TAG ="dialogListaLaboratorios"; //nombre de fragment

    private AsyncHttpClient clientAsinc;

    //objetos
    private EditText edtNombre, edtAcronimo,edtEdificio,edtFilas,edtColumnas,edtLat,edtAlt;
    public FloatingActionButton btn_guardar,btn_salir,btn_eliminar,btn_administrar,btn_encargados;
    private Spinner spEdificios;

    private String codigo="", edificio="", fil="", col="",
            nombre="", acronimo="", estado="", urlPost="",
             latitud="", longitud="";
    int progreso=0;
    @SuppressLint("RestrictedApi")

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_laboratorios, container, false);

        clientAsinc = new AsyncHttpClient();

        //objetos
        btn_guardar = view.findViewById(R.id.btnLab_guardar);
        btn_salir = view.findViewById(R.id.btnLab_salir);
        btn_eliminar = view.findViewById(R.id.btnLab_Eliminar);
        btn_administrar = view.findViewById(R.id.btnLab_administrar);
        btn_encargados = view.findViewById(R.id.btnLab_encargados);

        edtNombre = view.findViewById(R.id.edt_lab_nombre);
        edtAcronimo = view.findViewById(R.id.edt_lab_acronimo);

        edtFilas = view.findViewById(R.id.edt_lab_filas);
        edtColumnas = view.findViewById(R.id.edt_lab_columnas);
        edtLat = view.findViewById(R.id.edt_lab_lat);
        edtAlt = view.findViewById(R.id.edt_lab_alt);
        spEdificios = view.findViewById(R.id.sp_edificios);


        //ver de que proceso viene
        Bundle bundle = getArguments();
        String proceso = bundle.getString("proceso", "insert");



        if(proceso=="insert")
            btn_eliminar.setVisibility(View.GONE);
        else if(proceso=="delete")
            btn_guardar.setVisibility(View.GONE);

        llenarSpiner();

        //obtener el id de spiner seleccionado
        spEdificios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                clase_Edificio seleccion = (clase_Edificio) parent.getItemAtPosition(position);
                //Log.d("ID: " , String.valueOf(seleccion.getCodigo()));
                Log.i("id: " , String.valueOf(seleccion.getCodigoEdf()));

                edificio = String.valueOf(seleccion.getCodigoEdf());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Mensaje", "No se ha seleccionado");
            }
        });

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
        //para administrar
        btn_administrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mainCounten, new fm_LaboratorioMaquinas()).commit();
                getDialog().dismiss();
            }
        });
        //para encargados
        btn_encargados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mainCounten, new fm_EncargadosLab()).commit();
                getDialog().dismiss();
        }
        });

        return view;
    }

    // SPINER ::::::::::::::::
    private void llenarSpiner(){
        String url="http://104.248.185.225/practicaslab_utec/Edificio/Listado";

        clientAsinc.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if(statusCode==200)
                    cargarSpiner(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void cargarSpiner(String resp){

        ArrayList<clase_Edificio> listaArr = new ArrayList<clase_Edificio>();

        try {
            JSONObject obj = new JSONObject(resp);
            JSONArray lista =  obj.optJSONArray("resp");

            for (int i=0; i<lista.length(); i++) {
                JSONObject json_data = lista.getJSONObject(i);

                clase_Edificio edf = new clase_Edificio();
                edf.setNombre(json_data.getString("edf_nombre"));
                edf.setCodigoEdf(Integer.valueOf(json_data.getString("edf_codigo")));

                listaArr.add(edf);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //crear el Adapter.
        ArrayAdapter<clase_Edificio> adapter = new ArrayAdapter<clase_Edificio>(getContext(), android.R.layout.simple_dropdown_item_1line, listaArr);
        //Adapter a ListView para mostrar los datos.
        spEdificios.setAdapter(adapter);
    }



    //SUBCLASE  ::::::::::::::::::::::::::::::::::::::::::::::::::::

    //subclase para ejecutar en segundo plano la peticion ws
    private class invocarServicioRegistrar extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... arg0) {
            //tota la logica
            //servicio

            clase_Edificio edf = new clase_Edificio();
            codigo="0";
            estado="A";
            nombre = edtNombre.getText().toString();
            acronimo = edtAcronimo.getText().toString();
            fil = edtFilas.getText().toString();
            col = edtColumnas.getText().toString();

            latitud = edtLat.getText().toString();
            longitud = edtAlt.getText().toString();
            urlPost = "http://104.248.185.225/practicaslab_utec/Laboratorio/guardarDatos";

            Log.i("ID2", edificio);
            registrarServicio(codigo, edificio, acronimo, fil, col, nombre, latitud, longitud, urlPost);
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            btn_guardar.setClickable(true);
            Toast.makeText(getContext(), "Laboratorio registrado exitosamente", Toast.LENGTH_LONG).show();
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
    private void registrarServicio(String cod, String edi, String acr, String fil, String col, String nom, String lat, String lon,  String pUrl){

        HashMap<String, String> parametros = new HashMap<String, String>();
        parametros.put("cod", cod);
        parametros.put("edf", edi);
        parametros.put("acr", acr);
        parametros.put("fil", fil);
        parametros.put("col", col);
        parametros.put("nom", nom);
        parametros.put("lat", lat);
        parametros.put("lon", lon);


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

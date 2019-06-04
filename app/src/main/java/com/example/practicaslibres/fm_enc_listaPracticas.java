package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




public class fm_enc_listaPracticas extends Fragment {


    //INICIO: URL APIS --------------------------

    String addLaboratorios_api="http://104.248.185.225/practicaslab_utec/apis/admin/Laboratorio_api/guardarDatos";
    String listadoEdificios_api="http://104.248.185.225/practicaslab_utec/apis/admin/Edificio_api/listEdificios2";

    //FIN: URL APIS ------------------------------



    //definicion de objetos y nombre a fragment(tag)
    private static final String TAG="fragmentListaPrac";
    private Button btnOpenDialog, btnBuscar, btnNuevo;
    public TextView tvReciboDato;
    public ListView lvListado;

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
        lvListado = (ListView)view.findViewById(R.id.lvListadoPracticas_enc);

        //cargar listado
        listadoLaboratoriosWS ws = new listadoLaboratoriosWS();
        ws.execute();

        //Dialog para nuevo
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // log
                Log.d(TAG,"Mostrar Nuevo"); //log de fragment

                //dialog a invocar
                //fm_enc_dialogNuevoPracticas dialog = new fm_enc_dialogNuevoPracticas();
                //dialog.show(getFragmentManager(), "dialogNuevoPrac");

                Intent i = new Intent(getContext(), encProgramarPractica.class);
                startActivity(i);
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





    //clase asincrona para la carga de datos, peticiones en segundo plano
    private class listadoLaboratoriosWS extends AsyncTask<String,String,String> {
        private String resp;
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();

            try {

                URL url = new URL(listadoEdificios_api);
                urlConnection =(HttpURLConnection)url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;

                while ((line=reader.readLine())!=null){
                    result.append(line);
                }

            }catch (Exception ex){
                Toast.makeText(getContext(), "Error" + ex.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                urlConnection.disconnect();
            }

            return result.toString();

        }

        @Override
        protected void onPostExecute(String resp) {
            //terminar de cargar
            pdLoading.dismiss();

            //validar si el json no viene vacio
            if(resp.equals("")){
                Toast.makeText(getContext(), "No hay registros", Toast.LENGTH_LONG).show();
            }else
                jsonDatos(resp);

        }

        @Override
        protected void onPreExecute() {
            pdLoading.setMessage("\tCargando...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }
    }


    //parseo de la respuesta json, configuracion del json
    private void jsonDatos(String msgJson){

        List<String> contes = new ArrayList<String>();

        try {
            JSONObject obj = new JSONObject(msgJson);
            JSONArray lista =  obj.optJSONArray("resp");

            for (int i=0; i<lista.length(); i++) {
                JSONObject json_data = lista.getJSONObject(i);
                String edf = json_data.getString("lab_acronimo") + " --- " + json_data.getString("lab_nombre");
                contes.add(edf);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //crear el Adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, contes);
        //Adapter a ListView para mostrar los datos.
        lvListado.setAdapter(adapter);

    }








}

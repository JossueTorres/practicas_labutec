package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
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


public class fm_Edificios extends Fragment {

    private static final String TAG ="Edificios"; //nombre de fragment
    int progreso=0;
    String codEdificio="";
    Bundle bundle = new Bundle();

    //objetos
    public FloatingActionButton btn_agregar,btn_Refrescar;
    private ListView lvEdificios_adm;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fm__edificios, container, false);

        btn_agregar = view.findViewById(R.id.fab_agregarEnc);
        btn_Refrescar = view.findViewById(R.id.fab_RefreshEnc);

        lvEdificios_adm = (ListView)view.findViewById(R.id.lvEdificios_admin);
       listadoEdificiosWS ws = new listadoEdificiosWS();
       ws.execute();


        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"mensaje"); //log de fragment

                //dialog a invocar
                fm_dialogEdificio dialog = new fm_dialogEdificio();

                bundle.putString("proceso", "insert");
                dialog.setArguments(bundle);

                dialog.show(getFragmentManager(), "dialogListaEdificio");
            }
        });

        //refrescar data
        btn_Refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listadoEdificiosWS ws = new listadoEdificiosWS();
                ws.execute();

            }
        });

        lvEdificios_adm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //dialog a invocar
                fm_dialogEdificio dialog = new fm_dialogEdificio();

                bundle.putString("proceso", "delete");
                bundle.putString("codEdificio", codEdificio);
                dialog.setArguments(bundle);

                dialog.show(getFragmentManager(), "dialogListaEdificio");
            }
        });

        return view;
    }


    //clase asincrona para la carga de datos, peticiones en segundo plano
    private class listadoEdificiosWS extends AsyncTask<String,String,String> {
        private String resp;
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... strings) {

            StringBuilder result = new StringBuilder();

            try {

                URL url = new URL("http://104.248.185.225/practicaslab_utec/apis/admin/Edificio_api/listEdificios2");
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

    //parseo de la respuesta json
    private void jsonDatos(String msgJson){

        ArrayList<clase_Edificio> listaArr = new ArrayList<clase_Edificio>();

        List<String> contes = new ArrayList<String>();

        try {
            JSONObject obj = new JSONObject(msgJson);
            JSONArray lista =  obj.optJSONArray("resp");

            for (int i=0; i<lista.length(); i++) {
                JSONObject json_data = lista.getJSONObject(i);
                String edf = json_data.getString("edf_acronimo") + " - " + json_data.getString("edf_nombre");

                clase_Edificio objEdf = new clase_Edificio();
                objEdf.setCodigoEdf(Integer.valueOf(json_data.getString("edf_codigo")));

                listaArr.add(objEdf);

                contes.add(edf);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //crear el Adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, contes);
        //Adapter a ListView para mostrar los datos.
        lvEdificios_adm.setAdapter(adapter);

    }











}

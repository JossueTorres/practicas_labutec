package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class fm_PracticasDisponibles extends Fragment {

    private static final String TAG ="Edificios"; //nombre de fragment

    //objetos
    public FloatingActionButton btn_Refrescar;
    private ListView lvPracticas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fm__practicas_disponibles, container, false);


        btn_Refrescar = view.findViewById(R.id.fab_RefreshLbDis);
        lvPracticas = view.findViewById(R.id.lv_Practicas_Disponibles);

        obtenerEdificios_ws();
        btn_Refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerEdificios_ws();
            }
        });

        return view;
    }


    //consumir Servicio
    public void obtenerEdificios_ws() {

        //url
        String GET_URL="http://104.248.185.225/practicaslab_utec/apis/admin/Edificio_api/listEdificios2";
        final ProgressDialog loading = ProgressDialog.show(getContext(), "Por favor espere...", "Cargando Informacion",
                false, false);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, GET_URL,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //mostrar metodo
                        loading.dismiss();
                        mostrarListView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjReq);

    }

    //Mostrar el servicio en listview

    private void mostrarListView(JSONObject obj) {

        try {
            List<String> edificios = new ArrayList<String>();
            JSONArray lista = obj.optJSONArray("resp");
            for(int i =0; i<lista.length();i++){
                JSONObject json_data = lista.getJSONObject(i);
                String edf = json_data.getString("edf_acronimo") + " - " + json_data.getString("edf_nombre");
                edificios.add(edf);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, edificios);
            lvPracticas.setAdapter(adapter);

        } catch (Exception ex){
            Toast.makeText(getContext(), "Error al cargar la lista" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }finally {

        }
    }



}

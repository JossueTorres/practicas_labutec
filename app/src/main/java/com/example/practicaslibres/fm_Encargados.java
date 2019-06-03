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


public class fm_Encargados extends Fragment {
    //definicion de objetos y nombre a fragment(tag)
    private static final String TAG ="Encargados"; //nombre de fragment

    //objetos
    public FloatingActionButton btn_agregar,btn_Refrescar;
    private ListView lvEncargado;
    //metodo creado
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fm__encargados, container, false);
        btn_agregar = view.findViewById(R.id.fab_agregarEnc);
        btn_Refrescar = view.findViewById(R.id.fab_RefreshEnc);
        lvEncargado = view.findViewById(R.id.lv_Encargados);


        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"mensaje"); //log de fragment

                //dialog a invocar
                fm_dialogEncargado dialog = new fm_dialogEncargado();

                dialog.show(getFragmentManager(), "dialogListaEncargado");
            }
        });
        btn_Refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }






}

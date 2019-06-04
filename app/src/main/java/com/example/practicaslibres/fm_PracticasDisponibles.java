package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    Button btnMap;
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
        btnMap = view.findViewById(R.id.btnMapa);

        btn_Refrescar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), MapsActivity.class);
                startActivity(i);
            }
        });

        return view;
    }


    //consumir Servicio


    //Mostrar el servicio en listview





}

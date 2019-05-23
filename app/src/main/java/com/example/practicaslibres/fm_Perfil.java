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
import android.widget.TextView;
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


public class fm_Perfil extends Fragment {

    private static final String TAG ="Edificios"; //nombre de fragment

    //objetos
    public FloatingActionButton btn_edit;
    public TextView tv_nombre,tv_tipo,tv_email;
    private ListView lvEdificio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fm_perfil, container, false);

        btn_edit = view.findViewById(R.id.fab_editPerfil);
        tv_nombre = view.findViewById(R.id.tv_per_nombre);
        tv_tipo = view.findViewById(R.id.tv_per_tipo);
        tv_email = view.findViewById(R.id.tv_per_correo);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"mensaje"); //log de fragment

                //dialog a invocar
                fm_dialogPerfil dialog = new fm_dialogPerfil();

                dialog.show(getFragmentManager(), "dialogListaEdificio");
            }
        });
        return view;
    }



}

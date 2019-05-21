package com.example.practicaslibres;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class fm_Encargados extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fm__encargados, container, false);
    }
    public void boton (View view){
        Toast.makeText(this.getContext(), "hola", Toast.LENGTH_SHORT).show();
    }
}

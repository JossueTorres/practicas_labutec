package com.example.practicaslibres;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class fm_Edificios extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fm__edificios, container, false);
    }


    public void ClickLv(View v){
        //FragmentManager fragmentManager = getFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.mainCounten,new fm_Encargados()).commit();
        Toast.makeText(this.getContext(), "hola", Toast.LENGTH_SHORT).show();
    }
}

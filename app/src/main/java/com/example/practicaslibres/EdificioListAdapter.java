package com.example.practicaslibres;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EdificioListAdapter extends BaseAdapter {

    private Context context;
    private List<EdificioClase> edificiosList;

    //constructor

    public EdificioListAdapter(Context context, List<EdificioClase> edificiosList) {
        this.context = context;
        this.edificiosList = edificiosList;
    }

    @Override

    //Metodos implementados de base adapter********************
    public int getCount() {
        return edificiosList.size();
    }

    @Override
    public Object getItem(int position) {

        return edificiosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.items_edificios_list, null);
        TextView tvCodigo= v.findViewById(R.id.tvCodigo);
        TextView tvnombre = v.findViewById(R.id.tvNombre);

        //set text id para tag
        tvCodigo.setText(edificiosList.get(position).getCodigo());
        tvnombre.setText(edificiosList.get(position).getNombre());

        //guardar edificios en tag
        v.setTag(edificiosList.get(position).getId());
        return v;
    }
    //Metodos implementados de base adapter********************

}

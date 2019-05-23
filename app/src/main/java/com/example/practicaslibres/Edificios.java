package com.example.practicaslibres;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Edificios extends AppCompatActivity {

    private ListView lvEdificios;
    private EdificioListAdapter adapter;
    private List<EdificioClase> edificioList;

    String  sCodigo="", sNombre="", sDescripcion="";
    EditText edtCodigo, edtNombre, edtDescripcion;
    Button btnGuardar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edificios);

        //listview: se manda a llamar con un adaptador
        lvEdificios = findViewById(R.id.lvListadoEdificios);
        edificioList = new ArrayList<>();

        edificioList.add(new EdificioClase(1,"BJ", "BENITO JUAREZ", "0"));
        edificioList.add(new EdificioClase(2,"FM", "FRANCISCO MORAZAN", "0"));
        edificioList.add(new EdificioClase(3,"SB", "SIMON BOLIVAR", "0"));

        adapter = new EdificioListAdapter(getApplicationContext(), edificioList);
        lvEdificios.setAdapter(adapter);

        //PENDIENTE:  implementar la funcion que al dar clic sobre el listview me mande a otro form y el separador de registros
        
    }

    //Metodo que al dar clic en el boton invoca un AlertDialog
    public void nuevoEdificio(View v){

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Edificios.this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_edificos, null);

        edtNombre = mView.findViewById(R.id.edtEdificio_nombre);
        btnGuardar = findViewById(R.id.btnEnc_guardar);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

}

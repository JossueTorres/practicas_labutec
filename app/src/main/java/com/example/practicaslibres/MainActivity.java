package com.example.practicaslibres;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario, edtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);

    }

    public void Ingresar(View v){
        String usu = "" + edtUsuario.getText().toString(), cla = "" + edtClave.getText().toString();
        if(TextUtils.isEmpty(usu)){
            edtUsuario.requestFocus();
            edtUsuario.setError("El campo Usuario es requerido");
        }else if(TextUtils.isEmpty(cla)){
            edtUsuario.requestFocus();
            edtUsuario.setError("El campo Clave es requerido");
        }else{
            if (usu.equals("admin") && cla.equals("123")){
                Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                startActivity(i);
            }else if(usu.equals("encargado") && cla.equals("123")){

            }
        }
    }

}

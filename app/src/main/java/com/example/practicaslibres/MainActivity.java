package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario, edtClave;
    Button btnIngresar, btnLoguearse;
    private ProgressDialog progressDialog;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);

        btnIngresar = findViewById(R.id.btnIngresar);
        btnLoguearse = findViewById(R.id.btnLogearse);

        progressDialog = new ProgressDialog(this);

    }

    public void Ingresar(View v) {
        loguearUsuario();
        /*String usu = "" + edtUsuario.getText().toString(), cla = "" + edtClave.getText().toString();
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
                //FragmentManager fragmentManager = getSupportFragmentManager();
                //fragmentManager.beginTransaction().replace(R.id.mainCounten, new fm_Edificios()).commit();
            }else if(usu.equals("encargado") && cla.equals("123")){

                Intent i = new Intent(getApplicationContext(), Menu_Encargado.class);
                startActivity(i);

            }else if(usu.equals("alumno") && cla.equals("123")){

                Intent i = new Intent(getApplicationContext(), Menu_Alumno.class);
                startActivity(i);

            }*/
    }

    public void Loguearse(View v) {
        registrarUsuario();
    }

    private void registrarUsuario() {

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = edtUsuario.getText().toString().trim();
        final String password = edtClave.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Se ha registrado el usuario con el email: " + edtUsuario.getText(), Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void loguearUsuario() {

        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = edtUsuario.getText().toString().trim();
        String password = edtClave.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta en linea...");
        progressDialog.show();

        //loguear usuario
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //int pos = email.indexOf("@");
                            //String user = email.substring(0, pos);
                            Toast.makeText(MainActivity.this, "Bienvenidno: " + edtUsuario.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), MenuPrincipal.class);
                            startActivity(intencion);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(MainActivity.this, "Ese usuario ya existe ", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo ingresar verifique sus credenciales ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}


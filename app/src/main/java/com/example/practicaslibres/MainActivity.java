package com.example.practicaslibres;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText edtUsuario, edtClave;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Usuarios");

        edtUsuario = findViewById(R.id.edtUsuario);
        edtClave = findViewById(R.id.edtClave);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check si el user esta asignado en (non-null) .
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //logica aca
    }

    public void Ingresar(View v) {
        if (TextUtils.isEmpty(edtUsuario.getText().toString().trim())) {
            edtUsuario.setError("Campo obligatorio");
            edtUsuario.requestFocus();
        } else if (TextUtils.isEmpty(edtClave.getText().toString().trim())) {
            edtClave.setError("Campo obligatorio");
            edtClave.requestFocus();
        } else {
            final String email = edtUsuario.getText().toString().trim();
            String password = edtClave.getText().toString().trim();

            //hay que identificarse en firebase auth y si el auth es exitoso buscamos los datos de usuario por keyID
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Inicio exitoso

                                //objeto usuario que recupera el ID de usuario actual
                                FirebaseUser user = mAuth.getCurrentUser();
                                String idUser = user.getUid();
                                obtenerRolUsuario(idUser);

                                //Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                                //startActivity(i);

                                //obtenerRol(email);
                                //Toast.makeText(getApplicationContext(), "Bienvenido" + y, Toast.LENGTH_LONG).show();

                            } else {
                                // si falla mostrar.
                                Log.w("Fallo", "Error al iniciar", task.getException());
                                Toast.makeText(getApplicationContext(), "Auth fallo.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }
    }

    public void obtenerRolUsuario(String idUser) {
        //buscamos en el nodo el userID
        DatabaseReference mData2 = mDatabase.child(idUser);

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String rol = (dataSnapshot.child("rol").getValue().toString().trim());
                //Log.d("rol", rol);

                if (rol.equals("admin")) {
                    Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                    startActivity(i);
                } else if (rol.equals("encargado")) {
                    Intent i = new Intent(getApplicationContext(), Menu_Encargado.class);
                    startActivity(i);
                } else if (rol.equals("estudiante")) {
                    Intent i = new Intent(getApplicationContext(), Menu_Alumno.class);
                    startActivity(i);
                } else
                    Toast.makeText(getApplicationContext(), "Error al ingresar. Comuniquese con soporte tecnico", Toast.LENGTH_LONG).show();

                //Toast.makeText(getApplicationContext(), "Rol:" + rol , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        mData2.addValueEventListener(eventListener);
    }

}

package com.example.practicaslibres;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class fm_dialogEncargado extends DialogFragment {

    private static final String TAG = "dialogListaEdificio"; //nombre de fragment

    DatabaseReference DBReference;
    FirebaseAuth mAuth;

    //objetos
    private EditText edtNombre, edtCorreo, edtClave1, edtClave2;
    public TextView tvOk, tvCancel;
    public FloatingActionButton btn_guardar, btn_salir, btn_eliminar;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_encargados, container, false);

        //objetos
        btn_guardar = view.findViewById(R.id.btnEnc_guardar);
        btn_salir = view.findViewById(R.id.btnEnc_salir);
        btn_eliminar = view.findViewById(R.id.btnEnc_Eliminar);

        edtNombre = view.findViewById(R.id.edt_enc_nombre);
        edtCorreo = view.findViewById(R.id.edt_enc_correo);
        edtClave1 = view.findViewById(R.id.edt_enc_clave1);
        edtClave2 = view.findViewById(R.id.edt_enc_clave2);

        DBReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        mAuth = FirebaseAuth.getInstance();


        //Para cancelar
        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Saliendo...");
                getDialog().dismiss();
            }
        });


        //para confirmar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtNombre.getText().toString().trim())) {
                    edtNombre.setError("Campo obligatorio");
                    edtNombre.requestFocus();
                } else if (TextUtils.isEmpty(edtCorreo.getText().toString().trim())) {
                    edtCorreo.setError("Campo obligatorio");
                    edtCorreo.requestFocus();
                } else if (TextUtils.isEmpty(edtClave1.getText().toString().trim())) {
                    edtClave1.setError("Campo obligatorio");
                    edtClave1.requestFocus();
                } else if (TextUtils.isEmpty(edtClave2.getText().toString().trim())) {
                    edtClave2.setError("Debe confirmar la contraseña");
                    edtClave2.requestFocus();
                } else {

                    final String nombres = edtNombre.getText().toString();
                    final String correo = edtCorreo.getText().toString();
                    final String rol = "estudiante";
                    String password = edtClave1.getText().toString();

                    mAuth.createUserWithEmailAndPassword(correo, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        clase_Encargado enc = new clase_Encargado(
                                                nombres,
                                                correo,
                                                rol
                                        );

                                        FirebaseDatabase.getInstance().getReference("Usuarios")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(enc).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(getContext(), "Registro realizado exitosamente", Toast.LENGTH_LONG).show();

                                            }
                                        });

                                    } else {
                                        Toast.makeText(getContext(), "Error al registrar", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                    edtNombre.setText(null);
                    edtCorreo.setText(null);
                    edtClave1.setText(null);
                    edtClave2.setText(null);
                    edtNombre.requestFocus();
                }


            }
        });
        //para eliminar
        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Eliminando...");

            }
        });

        return view;
    }
}

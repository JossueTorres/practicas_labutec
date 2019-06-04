package com.example.practicaslibres;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class encProgramarPractica extends AppCompatActivity {

   //apis
   //----------------------------------------------------------------------------------------------------
    String baseUrl_online = metodos.baseUrlOnline.trim();
    String getBaseUrl_local = metodos.baseUrl.trim();

    String addConfig_api= baseUrl_online+"Configuracion_api/guardarDatos";
    //----------------------------------------------------------------------------------------------------


    private static final String TAG ="dialogNuevoPractica"; //nombre de fragment

    public FloatingActionButton btn_guardar,btn_salir;

    Button btnDesde,btnHasta,btnHoraInicio,btnHoraFin;
    EditText edtDesde, edtHasta, edtHoraInicio, edtHoraFin;
    CheckBox chkLu, chkMa, chkMi, chkJu, chkVi, chkSa, chkDo;

    //variables
    String codigo="", lab="", fechaInicio="", fechaFin="", horaInicio="", horaFin="", lun="", mar="", mie="", jue="", vie="", sab="", dom="", urlPost="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enc_programar_practica);

        //instanciar
        btnDesde= findViewById(R.id.btnDesdeNuevaPrac);
        btnHasta= findViewById(R.id.btnHastaNuevaPrac);
        btnHoraInicio= findViewById(R.id.btnHoraInicioNuevaPrac);
        btnHoraFin= findViewById(R.id.btnHoraFinNuevaPrac);
        btn_guardar = findViewById(R.id.btnGuardar_encNuevoPrac);
        btn_salir = findViewById(R.id.btnSalir_encNuevoPrac);

        edtDesde = findViewById(R.id.edtDesde_encNuevoPrac);
        edtHasta = findViewById(R.id.edtHasta_encNuevoPrac);
        edtHoraInicio = findViewById(R.id.edtHoraI_encNuevoPrac);
        edtHoraFin = findViewById(R.id.edtHoraF_encNuevoPrac);

        chkDo = findViewById(R.id.chkDo_encNuevoPrac);
        chkLu = findViewById(R.id.chkLu_encNuevoPrac);
        chkMa = findViewById(R.id.chkMa_encNuevoPrac);
        chkMi = findViewById(R.id.chkMi_encNuevoPrac);
        chkJu = findViewById(R.id.chkJu_encNuevoPrac);
        chkVi = findViewById(R.id.chkVi_encNuevoPrac);
        chkSa = findViewById(R.id.chkSa_encNuevoPrac);

        btnDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.fecha(encProgramarPractica.this, edtDesde);
            }
        });

        btnHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.fecha(encProgramarPractica.this,edtHasta);
            }
        });

        btnHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.hora(encProgramarPractica.this,edtHoraInicio);
            }
        });

        btnHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metodos.hora(encProgramarPractica.this,edtHoraFin);
            }
        });


        //guardar
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtDesde.getText().toString().equals("")){
                    edtDesde.setError("Campo requerido");
                    edtDesde.requestFocus();
                    return;
                }
                if (edtHasta.getText().toString().equals("")){
                    edtHasta.setError("Campo requerido");
                    edtHasta.requestFocus();
                    return;
                }
                if (edtHoraInicio.getText().toString().equals("")){
                    edtHoraInicio.setError("Campo requerido");
                    edtHoraInicio.requestFocus();
                    return;
                }
                if (edtHoraFin.getText().toString().equals("")){
                    edtHoraFin.setError("Campo requerido");
                    edtHoraFin.requestFocus();
                    return;
                }
                if (!hayUnDiaSeleccionado()){
                    Toast.makeText(getApplicationContext(),"TIENE QUE SELECCIONAR UN DIA\n" +
                            "COMO MINIMO",Toast.LENGTH_LONG).show();
                    return;
                }


                try {
                    if (!metodos.validarFechaDesde(edtDesde)){
                        edtDesde.setError("La fecha ya no es valida");
                        edtDesde.requestFocus();
                        //Toast.makeText(getContext(),"LA FECHA \"DESDE\" NO PUEDE SER ANTERIOR A LA DE ESTE DIA",Toast.LENGTH_LONG).show();
                        return;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    if (!metodos.validarFechaHasta(edtDesde,edtHasta)){
                        edtHasta.setError("Esta fecha debe ser mayor o igual que la anterior");
                        edtHasta.requestFocus();
                        //Toast.makeText(getContext(),"LA FECHA \"HASTA\" NO PUEDE SER ANTERIOR A LA DE ESTE DIA",Toast.LENGTH_LONG).show();
                        return;
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "Guardando...");

                String valorEntrada = edtDesde.getText().toString();

                //aca validaciones de cajas, pueden usar las utils
                if(!valorEntrada.equals("")){

                    Log.d("api_login", addConfig_api);
                    //logica aqui para guardar



                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
                    Date d = new Date();
                    String day = sdf.format(d);
                    int cantLetras = day.length();

                    String formatDay = day.substring(3,cantLetras);


                    //Toast.makeText(getApplicationContext(), "dia" + formatDay , Toast.LENGTH_LONG).show();
                    new RequestAsync().execute();

                    finish();
                }
            }
        });


        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public boolean hayUnDiaSeleccionado(){
        if (!chkLu.isChecked() && !chkMa.isChecked() && !chkMi.isChecked() && !chkJu.isChecked()
                && !chkVi.isChecked() && !chkSa.isChecked() && !chkDo.isChecked()){
            return false;
        }
        return true;
    }



    public class RequestAsync extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                //GET Request
                //return RequestHandler.sendGet("https://prodevsblog.com/android_get.php");

                // POST Request
                JSONObject parametros = new JSONObject();

               String codigo="", lab="", fechaInicio="", horaInicio="", fechaFin="", horaFin="",
                lun="1", mar="1", mie="1", jue="1", vie="1", sab="1", dom="1";

               fechaInicio = edtDesde.getText().toString().trim();
               horaInicio = edtHoraInicio.getText().toString().trim();
               fechaFin = edtHasta.getText().toString().trim();
               horaFin = edtHoraFin.getText().toString().trim();

               if(chkDo.isChecked())
                   dom="2";
               if(chkLu.isChecked())
                   lun="2";
               if(chkMa.isChecked())
                   mar = "2";
               if(chkMi.isChecked())
                   mie = "2";
               if(chkJu.isChecked())
                   jue="2";
               if(chkVi.isChecked())
                   vie="2";
               if (chkSa.isChecked())
                   sab = "2";


                parametros.put("cod", "0");
                parametros.put("lab", "1"); //valor que viene de firebase
                parametros.put("fini", fechaInicio);
                parametros.put("hini", horaInicio );
                parametros.put("ffin", fechaFin );
                parametros.put("hfin",horaFin );
                parametros.put("l", lun);
                parametros.put("m", mar);
                parametros.put("x", mie);
                parametros.put("j", jue);
                parametros.put("v", vie);
                parametros.put("s", sab);
                parametros.put("d", dom);

                return RequestHandler.sendPost(addConfig_api,parametros);
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }

        @Override
        protected void onPostExecute(String s) {
            if(s!=null){
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        }

    }












}

package com.example.practicaslibres;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;



public class ServicioTask extends AsyncTask<Void, Void, String> {
    //variables del hilo
    private Context httpContext;//contexto
    ProgressDialog progressDialog;//dialogo cargando
    public String resultadoapi="";
    public String linkrequestAPI="";//link  para consumir el servicio rest

    public String codigo="";
    public String laboratorio="";
    public String fechaInicio="";
    public String fechaFin="";
    public String horaInicio="";
    public String horaFin="";
    public String lun="";
    public String mar="";
    public String mie="";
    public String jue="";
    public String vie="";
    public String sab="";
    public String dom="";

    //constructor del hilo (Asynctask)
    public ServicioTask(Context ctx, String linkAPI, String codigox, String  laboratoriox, String fechaInicio, String fechaFin, String horaInicio,
                        String horaFin, String lun, String mar, String mie, String jue, String vie, String sab, String dom){
        this.httpContext=ctx;
        this.linkrequestAPI=linkAPI;


        this.codigo= codigox;
        this.laboratorio=laboratoriox;
        this.fechaInicio = fechaInicio;
        this.fechaFin=fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.lun = lun;
        this.mar= mar;
        this.mie= mie;
        this.jue=jue;
        this.vie=vie;
        this.sab=sab;
        this.dom=dom;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "Procesando Solicitud", "por favor, espere");
    }

    @Override
    protected String doInBackground(Void... params) {
        String result= null;

        String wsURL = linkrequestAPI;
        URL url = null;
        try {
            // se crea la conexion al api: http://localhost:15009/WEBAPIREST/api/persona
            url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            //crear el objeto json para enviar por POST
            JSONObject parametrosPost= new JSONObject();

            parametrosPost.put("cod",codigo);
            parametrosPost.put("lab",laboratorio);
            parametrosPost.put("fini",fechaInicio);
            parametrosPost.put("hini",horaInicio);
            parametrosPost.put("ffin",fechaFin);
            parametrosPost.put("hfin",horaFin);

            parametrosPost.put("l",lun);
            parametrosPost.put("m",mar);
            parametrosPost.put("x",mie);
            parametrosPost.put("j",jue);
            parametrosPost.put("v",vie);
            parametrosPost.put("s",sab);
            parametrosPost.put("d",dom);


            //DEFINIR PARAMETROS DE CONEXION
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("POST");// se puede cambiar por delete ,put ,etc
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);


            //OBTENER EL RESULTADO DEL REQUEST
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode=urlConnection.getResponseCode();// conexion OK?

                BufferedReader in= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuffer sb= new StringBuffer("");
                String linea="";
                while ((linea=in.readLine())!= null){
                    sb.append(linea);
                    break;

                }
                in.close();
                result= sb.toString();




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  result;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoapi=s;
        Toast.makeText(httpContext,resultadoapi,Toast.LENGTH_LONG).show();//mostrara una notificacion con el resultado del request

    }

    //FUNCIONES----------------------------------------------------------------------
    //Transformar JSON Obejct a String *******************************************
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }

}

package com.example.johnny.android_introduccion_consumo_web.Controlador;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.johnny.android_introduccion_consumo_web.Modelo.clsEstudiante;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Johnny on 26/07/2016.
 * https://paulallies.wordpress.com/2016/01/20/using-httpurlconnection-for-android/
 */
public class ctlEstudiante extends AsyncTask<Void, String, Boolean> {

    /*Variable que almacenara el resultado del servidor*/
    private StringBuffer buffer = null;

    /*Objeto*/
    private clsEstudiante estudiante;

    /*Variable que almacenara la accion que se realizara en el servidor*/
    private String accion;

    /*URL a la que se le realizara la peticion*/
    private final String ruta="http://192.168.1.11/prueba/solicitud.php";

    /*Referencia de la activity que envio la solicitud para mostrar los TOAST*/
    Activity activity;

    /*Referencia a la barra de carga*/
    ProgressBar carga;

    /*Constructor sin ID*/
    public ctlEstudiante(String codigo, String nombre, String apellido, String cedula, int semestre,
                         int edad, String accion, Activity activity, ProgressBar carga){
        this.accion = accion;
        this.activity = activity;
        this.carga = carga;
        estudiante = new clsEstudiante(codigo,nombre,apellido,cedula,edad,semestre);
    }

    /*Constructor recibiendo el ID*/
    public ctlEstudiante(int id, String codigo,String nombre,String apellido,String cedula,
                         int semestre,int edad, String accion, Activity activity, ProgressBar carga){
        this.accion = accion;
        this.carga = carga;
        this.activity = activity;
        estudiante = new clsEstudiante(id, codigo,nombre,apellido,cedula,edad,semestre);
    }


    @Override
    protected void onPreExecute() {
        //Se activa la barra de carga
        carga.setVisibility(View.VISIBLE);
    }



    @Override
    protected Boolean doInBackground(Void... params) {
        /*Se define un objeto para la conexion*/
        HttpURLConnection conn = null;
        /*Se define un buffer para leer los resultados de la conexion*/
        BufferedReader reader = null;

        try{
            /*Se crea la conexion*/

            /*Se establece un Objeto URL con la ruta definida*/
            URL url = new URL(ruta);
            /*Se añade la URL a la conexion*/
            conn = (HttpURLConnection) url.openConnection();
            /*Se define el tipo de conexion (GET - POST)*/
            conn.setRequestMethod("POST");


            /*Se añaden datos*/

            /*Se define en un objeto Uri.Builder para añadirle los datos que seran enviados*/
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("id", estudiante.getId()+"")
                    .appendQueryParameter("codigo", estudiante.getCodigo())
                    .appendQueryParameter("nombre", estudiante.getNombre())
                    .appendQueryParameter("cedula", estudiante.getCedula())
                    .appendQueryParameter("semestre", estudiante.getSemestre()+"")
                    .appendQueryParameter("edad", estudiante.getEdad()+"")
                    .appendQueryParameter("type", accion);
            /*Se codifican los datos añadidos con &&*/
            String query = builder.build().getEncodedQuery();

            /*Se define un OutputStream para añadir los datos definidos a la conexion como datos
            * de salida*/
            OutputStream os = conn.getOutputStream();
            /*Se pasan a un Buffer*/
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            /*Se añaden*/
            writer.write(query);
            /*Se confirma*/
            writer.flush();
            /*Se cierra la adicion*/
            writer.close();
            os.close();


            /*Se conecta, recibe datos y los lee*/

            /*Se ejecuta la conexion*/
            publishProgress("Se envian los datos");
            conn.connect();
            /*Con un InputStream Se obtienen los datos de la conexion*/
            InputStream stream = conn.getInputStream();
            /*Se define un reader para leer los datos, asociandolo al inputStream*/
            reader  = new BufferedReader(new InputStreamReader(stream));
            /*Se inicializa el buffer para almacenar como String los resultados*/
            buffer = new StringBuffer();
            /*Variable temporal para concatenar los datos leidos*/
            String line;
            /*Mientas lo que lea es diferente de vacio*/
            while((line = reader.readLine()) != null){
                /*Añadalos al buffer global*/
                buffer.append(line);
            }
        }catch (MalformedURLException e){
            publishProgress("Error mal estructura URL "+e.getMessage());
            e.printStackTrace();
            return false;
        }catch (IOException e){
            publishProgress("Error IO "+e.getMessage());
            e.printStackTrace();
            return false;
        }finally {
            /*Desconecta la conexion activa*/
            if(conn != null){
                conn.disconnect();
            }
            try{
                /*Cerramos los readers*/
                if(reader != null){
                    reader.close();
                }
            }catch (IOException e){
                publishProgress("Error al final "+e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }



    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(activity, values[0],
                Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onPostExecute(Boolean result) {
        /*Se oculta la barra de carga*/
        carga.setVisibility(View.INVISIBLE);

        try {

            if(result) {

                /*Como el resultado obtenido es un array JSON, se pasa el string JSON al array*/
                JSONArray jsonarray = new JSONArray(buffer.toString());

                /*Por cada elemento del JSON*/
                for (int i = 0; i < jsonarray.length(); i++) {
                    /*Se saca el objeto del array y se pasa a un objeto JSON*/
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    /*Se saca las variables del objeto*/
                    Toast.makeText(activity, "Operacion exitosa "+jsonobject.getString("res"),
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(activity, "Error en la operacion",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            Toast.makeText(activity, "Error tratanto el resultado "+e.getMessage(),
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }

}

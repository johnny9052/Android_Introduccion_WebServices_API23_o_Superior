package com.example.johnny.android_introduccion_consumo_web;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.johnny.android_introduccion_consumo_web.Controlador.ctlEstudiante;


public class MainActivity extends AppCompatActivity {


    ctlEstudiante controller;

    EditText txtCodigo;
    EditText txtNombre;
    EditText txtApellido;
    EditText txtCedula;
    EditText txtEdad;
    EditText txtSemestre;

    ProgressBar carga;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtApellido = (EditText)findViewById(R.id.txtApellido);
        txtCedula = (EditText)findViewById(R.id.txtCedula);
        txtEdad = (EditText)findViewById(R.id.txtEdad);
        txtSemestre = (EditText)findViewById(R.id.txtSemestre);
        carga = (ProgressBar)findViewById(R.id.progressBar);
        carga.setVisibility(View.INVISIBLE);
    }


    public void guardar(View view){

        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String apellido = txtApellido.getText().toString();
        String cedula = txtCedula.getText().toString();
        int edad = Integer.parseInt(txtEdad.getText().toString());
        int semestre = Integer.parseInt(txtSemestre.getText().toString());

        /*Se inicializa el controlador mandando la accion de SAVE*/
        controller = new ctlEstudiante(codigo,nombre,apellido,cedula,edad,semestre,"save",this,carga);
        /*Se inicia la petecion*/
        controller.execute();
    }
}

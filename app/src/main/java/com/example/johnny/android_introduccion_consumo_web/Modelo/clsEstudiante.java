package com.example.johnny.android_introduccion_consumo_web.Modelo;

/**
 * Created by Johnny on 26/07/2016.
 */
public class clsEstudiante {

    private int id;
    private String codigo;
    private String nombre;
    private String apellido;
    private String cedula;
    private int semestre;
    private int edad;


    public clsEstudiante(int id,String codigo,String nombre,String apellido,String cedula,int semestre,int edad){
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido= apellido;
        this.cedula = cedula;
        this.semestre = semestre;
        this.edad = edad;
    }



    public clsEstudiante(String codigo,String nombre,String apellido,String cedula,int semestre,int edad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido= apellido;
        this.cedula = cedula;
        this.semestre = semestre;
        this.edad = edad;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

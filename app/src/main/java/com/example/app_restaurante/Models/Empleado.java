package com.example.app_restaurante.Models;

import android.widget.Button;

public class Empleado {

    private String Nombre;
    private String Apellidos;

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    private String UID;

    public Empleado() {
    }

    public Empleado(String UID, String Nombre,  String Apellidos) {
        setUID(UID);
        setNombre(Nombre);
        setApellidos(Apellidos);
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
}

package com.example.app_restaurante.Models;

public class Empleado {

    private String Nombre;
    private String Apellidos;

    public Empleado() {
    }

    public Empleado(String Nombre,  String Apellids) {
        this.Nombre =Nombre;
        this.Apellidos = Apellids;
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

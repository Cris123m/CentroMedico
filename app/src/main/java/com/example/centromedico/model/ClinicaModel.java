package com.example.centromedico.model;

public class ClinicaModel {
    private String idClinica;
    private String nombre;
    private String direccion;

    public ClinicaModel() {
    }

    public ClinicaModel(String idClinica, String nombre, String direccion) {
        this.idClinica = idClinica;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(String idClinica) {
        this.idClinica = idClinica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

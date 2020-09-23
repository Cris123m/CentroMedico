package com.example.centromedico.model;

public class EstadioModel {
    private String idEstadio;
    private String nombre;
    private String capacidad;
    private double latitud;
    private double longitud;

    public EstadioModel() {
    }

    public EstadioModel(String idEstadio, String nombre, String capacidad, double latitud, double longitud) {
        this.idEstadio = idEstadio;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(String idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}

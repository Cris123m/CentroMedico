package com.example.centromedico.model;

public class PosicionModel {
    private String idPosicion;
    private int puesto;
    private ClinicaModel clinica;

    public PosicionModel() {
    }

    public PosicionModel(String idPosicion, int puesto, ClinicaModel clinica) {
        this.idPosicion = idPosicion;
        this.puesto = puesto;
        this.clinica = clinica;
    }

    public String getIdPosicion() {
        return idPosicion;
    }

    public void setIdPosicion(String idPosicion) {
        this.idPosicion = idPosicion;
    }

    public int getPuesto() {
        return puesto;
    }

    public void setPuesto(int puesto) {
        this.puesto = puesto;
    }

    public ClinicaModel getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaModel clinica) {
        this.clinica = clinica;
    }
}

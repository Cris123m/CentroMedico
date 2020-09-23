package com.example.centromedico.model;

public class PacienteModel {
    private String idPaciente;
    private String cedula;
    private int historiaClinica;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private ClinicaModel clinica;

    public PacienteModel() {
    }

    public PacienteModel(String idPaciente, String cedula, int historiaClinica, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, ClinicaModel clinica) {
        this.idPaciente = idPaciente;
        this.cedula = cedula;
        this.historiaClinica = historiaClinica;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.clinica = clinica;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(int historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public ClinicaModel getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaModel clinica) {
        this.clinica = clinica;
    }
}

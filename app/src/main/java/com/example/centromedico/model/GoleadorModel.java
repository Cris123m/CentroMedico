package com.example.centromedico.model;

public class GoleadorModel {
    private String idGoleador;
    private PacienteModel paciente;
    private int goles;

    public GoleadorModel() {
    }

    public GoleadorModel(String idGoleador, PacienteModel paciente, int goles) {
        this.idGoleador = idGoleador;
        this.paciente = paciente;
        this.goles = goles;
    }

    public String getIdGoleador() {
        return idGoleador;
    }

    public void setIdGoleador(String idGoleador) {
        this.idGoleador = idGoleador;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }
}

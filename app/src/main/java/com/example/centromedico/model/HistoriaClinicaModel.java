package com.example.centromedico.model;

public class HistoriaClinicaModel {
    private String idHistoriaClinica;
    private PacienteModel paciente;

    public HistoriaClinicaModel() {
    }

    public HistoriaClinicaModel(String idHistoriaClinica, PacienteModel paciente) {
        this.idHistoriaClinica = idHistoriaClinica;
        this.paciente = paciente;
    }

    public String getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(String idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }
}

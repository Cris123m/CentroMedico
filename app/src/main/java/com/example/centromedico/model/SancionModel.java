package com.example.centromedico.model;

public class SancionModel {
    private String idSancion;
    private PacienteModel paciente;
    private  String detalle;

    public SancionModel() {
    }

    public SancionModel(String idSancion, PacienteModel paciente, String detalle) {
        this.idSancion = idSancion;
        this.paciente = paciente;
        this.detalle = detalle;
    }

    public String getIdSancion() {
        return idSancion;
    }

    public void setIdSancion(String idSancion) {
        this.idSancion = idSancion;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}

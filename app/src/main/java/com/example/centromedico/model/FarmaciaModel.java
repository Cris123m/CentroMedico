package com.example.centromedico.model;

public class FarmaciaModel {
    private String idFarmacia;
    private PacienteModel paciente;
    private String descripcion;

    public FarmaciaModel() {
    }

    public FarmaciaModel(String idFarmacia, PacienteModel paciente, String descripcion) {
        this.idFarmacia = idFarmacia;
        this.paciente = paciente;
        this.descripcion = descripcion;
    }

    public String getIdFarmacia() {
        return idFarmacia;
    }

    public void setIdFarmacia(String idFarmacia) {
        this.idFarmacia = idFarmacia;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

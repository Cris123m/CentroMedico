package com.example.centromedico.model;

public class DetalleHistoriaClinicaModel {
    private String idDetalleHistoriaClinica;
    private HistoriaClinicaModel historiaClinica;
    private MedicoModel medico;
    private String procedimiento;
    private String detalle;

    public DetalleHistoriaClinicaModel() {
    }

    public DetalleHistoriaClinicaModel(String idDetalleHistoriaClinica, HistoriaClinicaModel historiaClinica, MedicoModel medico, String procedimiento, String detalle) {
        this.idDetalleHistoriaClinica = idDetalleHistoriaClinica;
        this.historiaClinica = historiaClinica;
        this.medico = medico;
        this.procedimiento = procedimiento;
        this.detalle = detalle;
    }

    public String getIdDetalleHistoriaClinica() {
        return idDetalleHistoriaClinica;
    }

    public void setIdDetalleHistoriaClinica(String idDetalleHistoriaClinica) {
        this.idDetalleHistoriaClinica = idDetalleHistoriaClinica;
    }

    public HistoriaClinicaModel getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinicaModel historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel medico) {
        this.medico = medico;
    }

    public String getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(String procedimiento) {
        this.procedimiento = procedimiento;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
}

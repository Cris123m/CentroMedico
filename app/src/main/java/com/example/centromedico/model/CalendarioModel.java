package com.example.centromedico.model;

import java.util.Date;

public class CalendarioModel {
    private String idCalendario;
    private Date fecha;
    private String idFase;
    private MedicoModel medico;
    private PacienteModel paciente;

    public CalendarioModel() {
    }

    public CalendarioModel(String idCalendario, Date fecha, String idFase, MedicoModel medico, PacienteModel paciente) {
        this.idCalendario = idCalendario;
        this.fecha = fecha;
        this.idFase = idFase;
        this.medico = medico;
        this.paciente = paciente;
    }

    public String getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(String idCalendario) {
        this.idCalendario = idCalendario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdFase() {
        return idFase;
    }

    public void setIdFase(String idFase) {
        this.idFase = idFase;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel medico) {
        this.medico = medico;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }
}

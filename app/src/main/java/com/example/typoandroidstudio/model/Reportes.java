package com.example.typoandroidstudio.model;

import java.util.List;

public class Reportes {
    private long id;
    private String mes_reporte;
    private long total_agendamientos;
    private float porcentaje_cumplimiento;
    private List<Agendamiento> agendamientos_cumplidos;
    private List<Agendamiento> agendamientos_no_cumplidos;

    public Reportes(long id, String mes_reporte, long total_agendamientos, float porcentaje_cumplimiento, List<Agendamiento> agendamientos_cumplidos, List<Agendamiento> agendamientos_no_cumplidos) {
        this.id = id;
        this.mes_reporte = mes_reporte;
        this.total_agendamientos = total_agendamientos;
        this.porcentaje_cumplimiento = porcentaje_cumplimiento;
        this.agendamientos_cumplidos = agendamientos_cumplidos;
        this.agendamientos_no_cumplidos = agendamientos_no_cumplidos;
    }

    public Reportes(String mes_reporte, long total_agendamientos, float porcentaje_cumplimiento, List<Agendamiento> agendamientos_cumplidos, List<Agendamiento> agendamientos_no_cumplidos) {
        this.mes_reporte = mes_reporte;
        this.total_agendamientos = total_agendamientos;
        this.porcentaje_cumplimiento = porcentaje_cumplimiento;
        this.agendamientos_cumplidos = agendamientos_cumplidos;
        this.agendamientos_no_cumplidos = agendamientos_no_cumplidos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMes_reporte() {
        return mes_reporte;
    }

    public void setMes_reporte(String mes_reporte) {
        this.mes_reporte = mes_reporte;
    }

    public long getTotal_agendamientos() {
        return total_agendamientos;
    }

    public void setTotal_agendamientos(long total_agendamientos) {
        this.total_agendamientos = total_agendamientos;
    }

    public float getPorcentaje_cumplimiento() {
        return porcentaje_cumplimiento;
    }

    public void setPorcentaje_cumplimiento(float porcentaje_cumplimiento) {
        this.porcentaje_cumplimiento = porcentaje_cumplimiento;
    }

    public List<Agendamiento> getAgendamientos_cumplidos() {
        return agendamientos_cumplidos;
    }

    public void setAgendamientos_cumplidos(List<Agendamiento> agendamientos_cumplidos) {
        this.agendamientos_cumplidos = agendamientos_cumplidos;
    }

    public List<Agendamiento> getAgendamientos_no_cumplidos() {
        return agendamientos_no_cumplidos;
    }

    public void setAgendamientos_no_cumplidos(List<Agendamiento> agendamientos_no_cumplidos) {
        this.agendamientos_no_cumplidos = agendamientos_no_cumplidos;
    }
}

package com.example.typoandroidstudio.model;

import java.util.List;

public class Mascotalogros {
    private long id;
    private String tipoLogro;
    private List<Logros> tiempoSemanal;

    public Mascotalogros(long id, String tipoLogro, List<Logros> tiempoSemanal) {
        this.id = id;
        this.tipoLogro = tipoLogro;
        this.tiempoSemanal = tiempoSemanal;
    }

    public Mascotalogros(String tipoLogro, List<Logros> tiempoSemanal) {
        this.tipoLogro = tipoLogro;
        this.tiempoSemanal = tiempoSemanal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoLogro() {
        return tipoLogro;
    }

    public void setTipoLogro(String tipoLogro) {
        this.tipoLogro = tipoLogro;
    }

    public List<Logros> getTiempoSemanal() {
        return tiempoSemanal;
    }

    public void setTiempoSemanal(List<Logros> tiempoSemanal) {
        this.tiempoSemanal = tiempoSemanal;
    }

    @Override
    public String toString() {
        return "Mascotalogros{" +
                "id=" + id +
                ", tipoLogro='" + tipoLogro + '\'' +
                ", tiempoSemanal=" + tiempoSemanal +
                '}';
    }
}


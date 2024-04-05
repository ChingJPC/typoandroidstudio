package com.example.typoandroidstudio.model;

public class Logros {
    private long id;
    private String tipoLogro;
    private String tiempoSemanal;

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

    public String getTiempoSemanal() {
        return tiempoSemanal;
    }

    public void setTiempoSemanal(String tiempoSemanal) {
        this.tiempoSemanal = tiempoSemanal;
    }

    @Override
    public String toString() {
        return "Logros{" +
                "id=" + id +
                ", tipoLogro='" + tipoLogro + '\'' +
                ", tiempoSemanal='" + tiempoSemanal + '\'' +
                '}';
    }
}


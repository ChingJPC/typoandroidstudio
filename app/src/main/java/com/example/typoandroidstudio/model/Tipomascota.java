package com.example.typoandroidstudio.model;

public class Tipomascota {
    private long id;
    private String Tipo_Mascota;

    public Tipomascota(long id, String tipo_Mascota) {
        this.id = id;
        Tipo_Mascota = tipo_Mascota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo_Mascota() {
        return Tipo_Mascota;
    }

    public void setTipo_Mascota(String tipo_Mascota) {
        Tipo_Mascota = tipo_Mascota;
    }
}


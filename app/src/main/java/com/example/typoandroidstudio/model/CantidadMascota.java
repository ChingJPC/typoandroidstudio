package com.example.typoandroidstudio.model;

import java.util.List;

public class CantidadMascota {
    private List<Tipomascota> Tipo_Mascota;
    long cantidad;

    public CantidadMascota(List<Tipomascota> tipo_Mascota, long cantidad) {
        Tipo_Mascota = tipo_Mascota;
        this.cantidad = cantidad;
    }

    public List<Tipomascota> getTipo_Mascota() {
        return Tipo_Mascota;
    }

    public void setTipo_Mascota(List<Tipomascota> tipo_Mascota) {
        Tipo_Mascota = tipo_Mascota;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CantidadMascota{" +
                "Tipo_Mascota=" + Tipo_Mascota +
                ", cantidad=" + cantidad +
                '}';
    }
}

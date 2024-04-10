package com.example.typoandroidstudio.model;

public class CantidadMascota {
    String Tipo_Mascota;
    long cantidad;

    public CantidadMascota(String tipo_Mascota, long cantidad) {
        Tipo_Mascota = tipo_Mascota;
        this.cantidad = cantidad;
    }

    public String getTipo_Mascota() {
        return Tipo_Mascota;
    }

    public void setTipo_Mascota(String tipo_Mascota) {
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
        return "CatidadMascota{" +
                "Tipo_Mascota='" + Tipo_Mascota + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}

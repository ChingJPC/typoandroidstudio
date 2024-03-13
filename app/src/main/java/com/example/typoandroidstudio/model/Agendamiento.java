package com.example.typoandroidstudio.model;

public class Agendamiento {
    private String tiempo_asignado_actividad;
    private String Fecha_Agendamiento;
    private boolean cumplida;
    private long infoMascota_id;
    private long actividad_id;
    private long user_id;

    public String getTiempo_asignado_actividad() {
        return tiempo_asignado_actividad;
    }

    public void setTiempo_asignado_actividad(String tiempo_asignado_actividad) {
        this.tiempo_asignado_actividad = tiempo_asignado_actividad;
    }

    public String getFecha_Agendamiento() {
        return Fecha_Agendamiento;
    }

    public void setFecha_Agendamiento(String fecha_Agendamiento) {
        Fecha_Agendamiento = fecha_Agendamiento;
    }

    public boolean isCumplida() {
        return cumplida;
    }

    public void setCumplida(boolean cumplida) {
        this.cumplida = cumplida;
    }

    public long getInfoMascota_id() {
        return infoMascota_id;
    }

    public void setInfoMascota_id(long infoMascota_id) {
        this.infoMascota_id = infoMascota_id;
    }

    public long getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(long actividad_id) {
        this.actividad_id = actividad_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}

package com.example.typoandroidstudio.model;

public class Agendamiento {
    private long id;
    private String tiempo_asignado_actividad;
    private String Fecha_Agendamiento;
    private byte cumplida;
    private long infoMascota_id;
    private long actividad_id;
    private long user_id;
    private String nombre_actividad;

    public Agendamiento(long id, String tiempo_asignado_actividad, String fecha_Agendamiento, byte cumplida, long infoMascota_id, long actividad_id, long user_id) {
        this.id = id;
        this.tiempo_asignado_actividad = tiempo_asignado_actividad;
        Fecha_Agendamiento = fecha_Agendamiento;
        this.cumplida = cumplida;
        this.infoMascota_id = infoMascota_id;
        this.actividad_id = actividad_id;
        this.user_id = user_id;
    }

    public Agendamiento(String tiempo_asignado_actividad, String fecha_Agendamiento, byte cumplida, long infoMascota_id, long actividad_id, long user_id) {
        this.tiempo_asignado_actividad = tiempo_asignado_actividad;
        Fecha_Agendamiento = fecha_Agendamiento;
        this.cumplida = cumplida;
        this.infoMascota_id = infoMascota_id;
        this.actividad_id = actividad_id;
        this.user_id = user_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public byte isCumplida() {
        return cumplida;
    }

    public void setCumplida(byte cumplida) {
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

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    @Override
    public String toString() {
        return "Agendamiento{" +
                "id=" + id +
                ", tiempo_asignado_actividad='" + tiempo_asignado_actividad + '\'' +
                ", Fecha_Agendamiento='" + Fecha_Agendamiento + '\'' +
                ", cumplida=" + cumplida +
                ", infoMascota_id=" + infoMascota_id +
                ", actividad_id=" + actividad_id +
                ", user_id=" + user_id +
                ", nombre_actividad='" + nombre_actividad + '\'' +
                '}';
    }
}

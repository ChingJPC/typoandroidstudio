package com.example.typoandroidstudio.model;

public class Actividad {
    private long id;
    private String nombre_actividad;
    private String descripcion_actividad;

    public Actividad(long id, String nombre_actividad, String descripcion_actividad) {
        this.id = id;
        this.nombre_actividad = nombre_actividad;
        this.descripcion_actividad = descripcion_actividad;
    }

    public Actividad(String nombre_actividad, String descripcion_actividad) {
        this.nombre_actividad = nombre_actividad;
        this.descripcion_actividad = descripcion_actividad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public String getDescripcion_actividad() {
        return descripcion_actividad;
    }

    public void setDescripcion_actividad(String descripcion_actividad) {
        this.descripcion_actividad = descripcion_actividad;
    }

    @Override
    public String toString() {
        return  nombre_actividad;
    }
}

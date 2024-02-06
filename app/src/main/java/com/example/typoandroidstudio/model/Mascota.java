package com.example.typoandroidstudio.model;

import androidx.annotation.NonNull;

public class Mascota {
    private long id;
    private String Nombre_Mascota;
    private long Edad;
    private String Raza;
    private long Peso;
    private float Tamaño;
    private String Sexo;

    private Tipomascota tipomascota;

    public Tipomascota getTipomascota() {
        return tipomascota;
    }

    public void setTipomascota(Tipomascota tipomascota) {
        this.tipomascota = tipomascota;
    }

    public Mascota(String nombre_mascota, long edad, String raza, long peso, float tamaño, String sexo) {
        Nombre_Mascota = nombre_mascota;
        Edad = edad;
        Raza = raza;
        Peso = peso;
        Tamaño = tamaño;
        Sexo = sexo;
    }

    public Mascota(long id, String nombre_mascota, long edad, String raza, long peso, float tamaño, String sexo) {
        this.id = id;
        Nombre_Mascota = nombre_mascota;
        Edad = edad;
        Raza = raza;
        Peso = peso;
        Tamaño = tamaño;
        Sexo = sexo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_Mascota() {
        return Nombre_Mascota;
    }

    public void setNombre_Mascota(String nombre_mascota) {
        Nombre_Mascota = nombre_mascota;
    }

    public long getEdad() {
        return Edad;
    }

    public void setEdad(long edad) {
        Edad = edad;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public long getPeso() {
        return Peso;
    }

    public void setPeso(long peso) {
        Peso = peso;
    }

    public float getTamaño() {
        return Tamaño;
    }

    public void setTamaño(float tamaño) {
        Tamaño = tamaño;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    @NonNull
    @Override
    public String toString() {
        return Nombre_Mascota;
    }
}

package com.example.typoandroidstudio.model;

import java.io.Serializable;

public class User implements Serializable {
    private long id;
    private String name;
    private String apellido;
    private String telefono;
    private String fecha_nacimiento;
    private String email;
    private String password;

    public User(long id, String name, String apellido, String telefono, String fecha_nacimiento, String email, String password) {
        this.id = id;
        this.name = name;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;}

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fecha_nacimiento='" + fecha_nacimiento + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

package com.Learnit.model;

public abstract class Person {
    protected String nombre;
    protected String correo;
    protected String usuario;

    public Person() {
    }

    public Person(String nombre, String correo, String usuario) {
        this.nombre = nombre;
        this.correo = correo;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}

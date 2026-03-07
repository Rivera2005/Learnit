package com.Learnit.model;

public abstract class Person {
    protected String nombre;
    protected String correo;
    protected String usuario;
    protected String password;

    public Person() {
    }

    public Person(String nombre, String correo, String usuario, String password) {
        this.nombre = nombre;
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

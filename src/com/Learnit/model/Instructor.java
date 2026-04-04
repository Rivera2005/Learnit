package com.Learnit.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String especialidad;
    private List<Course> cursosAsignados = new ArrayList<>();

    public Instructor() {
    }

    public Instructor(String nombre, String correo, String usuario, String password) {
        super(nombre, correo, usuario, password);
    }

    public Instructor(String especialidad, List<Course> cursosAsignados) {
        this.especialidad = especialidad;
        this.cursosAsignados = cursosAsignados;
    }

    public Instructor(String nombre, String correo, String usuario, String password, String especialidad, List<Course> cursosAsignados) {
        super(nombre, correo, usuario, password);
        this.especialidad = especialidad;
        this.cursosAsignados = cursosAsignados;
    }


    @Override
    public String toString() {
        return "===== Instructor Registrado =====\n" +
                "Nombre: " + getNombre() + "\n" +
                "Correo: " + getCorreo() + "\n" +
                "Usuario: " + getUsuario() + "\n" +
                "Cursos asignados: " + (cursosAsignados != null ? cursosAsignados.size() : 0) + "\n" +
                "=================================";
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Course> getCursosAsignados() {
        return cursosAsignados;
    }

    public void setCursosAsignados(List<Course> cursosAsignados) {
        this.cursosAsignados = cursosAsignados;
    }
}

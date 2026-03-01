package com.Learnit.model;

import java.util.List;

public class Instructor extends Person {
    private String especialidad;
    private List<Course> cursosAsignados;

    public Instructor() {
    }

    public Instructor(String especialidad, List<Course> cursosAsignados) {
        this.especialidad = especialidad;
        this.cursosAsignados = cursosAsignados;
    }

    public Instructor(String nombre, String correo, String usuario, String especialidad, List<Course> cursosAsignados) {
        super(nombre, correo, usuario);
        this.especialidad = especialidad;
        this.cursosAsignados = cursosAsignados;
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

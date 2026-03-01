package com.Learnit.model;

import java.util.List;

public class Student extends Person {

    private List<Enrollment> cursosInscritos;

    public Student() {
    }

    public Student(List<Enrollment> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }

    public Student(String nombre, String correo, String usuario, List<Enrollment> cursosInscritos) {
        super(nombre, correo, usuario);
        this.cursosInscritos = cursosInscritos;
    }

    public List<Enrollment> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Enrollment> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
}

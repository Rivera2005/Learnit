package com.Learnit.model;

import java.util.List;

public class Student extends Person {

    private List<Enrollment> cursosInscritos;

    public Student() {
    }

    public Student(String nombre, String correo, String usuario, String password) {
        super(nombre, correo, usuario, password);
    }

    public Student(List<Enrollment> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }

    public Student(String nombre, String correo, String usuario, String password, List<Enrollment> cursosInscritos) {
        super(nombre, correo, usuario, password);
        this.cursosInscritos = cursosInscritos;
    }

    public List<Enrollment> getCursosInscritos() {
        return cursosInscritos;
    }

    public void setCursosInscritos(List<Enrollment> cursosInscritos) {
        this.cursosInscritos = cursosInscritos;
    }
}

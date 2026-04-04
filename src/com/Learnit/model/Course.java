package com.Learnit.model;

import java.util.List;
import java.util.ArrayList;

public class Course {
    private String codigoCurso;
    private String nombre;
    private String descripcion;
    private Double duracion;
    private String materia;
    private Instructor instructor;

    public Course() {
    }

    public Course(String codigoCurso, String nombre, String descripcion, Double duracion, String materia) {
        this.codigoCurso = codigoCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.materia = materia;
    }

    public Course(String codigoCurso, String nombre, String descripcion, Double duracion, String materia, Instructor instructor) {
        this.codigoCurso = codigoCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.materia = materia;
        this.instructor = instructor;
    }




    // Getters y Setter

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString() {
        return "=== CURSO ===" +
                "\nCódigo: " + codigoCurso +
                "\nNombre: " + nombre +
                "\nDescripción: " + descripcion +
                "\nDuración: " + duracion +
                "\nMateria: " + materia +
                "\nInstructor: " + (instructor != null ? instructor.getNombre() : "Sin asignar");
    }
}


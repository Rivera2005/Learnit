package com.Learnit.service;

import com.Learnit.model.Course;
import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();

    public Course registerCourse(String codigo, String nombre, String descripcion, Double duracion, String materia, Instructor instructor){
        if (buscarPorCodigo(codigo) != null){
            return null;
        }

        if (nombre == null || nombre.trim().isEmpty()) return null;

        if (descripcion == null || descripcion.trim().isEmpty()) return null;

        if (duracion <= 0) return null;

        Course course = new Course(codigo, nombre, descripcion, duracion, materia, instructor);
        courses.add(course);
        instructor.getCursosAsignados().add(course);
        return course;
    }

    public Course buscarPorCodigo(String codigo){
        for (Course c: courses){
            if (c.getCodigoCurso().equals(codigo)){
                return c;
            }
        }
        return null;
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }
}
package com.Learnit.service;

import com.Learnit.model.Course;
import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class CourseService {

    private ArrayList<Course> courses = new ArrayList<>();

    /*public void registerCourse(String codigo, String nombre, String descripcion, Double duracion, String materia){
        Course course = new Course(codigo, nombre, descripcion, duracion, materia);
        courses.add(course);
        //instructor.getCursosAsignados().add(course);
        System.out.println("Curso registrado correctamente :)");
    }*/
    public void registerCourse(String codigo, String nombre, String descripcion, Double duracion, String materia, Instructor instructor){
        Course course = new Course(codigo, nombre, descripcion, duracion, materia, instructor);
        courses.add(course);
        instructor.getCursosAsignados().add(course);
        System.out.println("Curso registrado correctamente :)");
        System.out.println(course.toString());
        System.out.println("------------------");
        System.out.println(instructor.toString());
    }


    public ArrayList<Course> getCourses() {
        return courses;
    }
}
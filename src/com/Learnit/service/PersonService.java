package com.Learnit.service;

import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;

import java.util.ArrayList;

public class PersonService {

    private ArrayList<Person> persons = new ArrayList<>();

    public void registerStudent(String nombre, String correo, String usuario, String password){
        Student student = new Student(nombre, correo, usuario, password);
        persons.add(student);
    }

    public void registerInstructor(String nombre, String correo, String usuario, String password){
        Instructor instructor = new Instructor(nombre, correo, usuario, password);
        persons.add(instructor);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }
}

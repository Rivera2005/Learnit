package com.Learnit.service;

import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;

import java.util.ArrayList;

public class PersonService {

    private ArrayList<Person> persons = new ArrayList<>();

    public Student registerStudent(String nombre, String correo, String usuario, String password){

        // Validación datos null o que tenga cierto tipo de caracter
        if (!datosValidos(nombre, correo, usuario, password)) {
            return null;
        }

        // Validación para evitar repteciones
        if (buscarPorUsuario(usuario) != null){
            return null;
        }

        Student student = new Student(nombre.trim(), correo.trim(), usuario.trim(), password);
        persons.add(student);
        return student;
    }

    public Instructor registerInstructor(String nombre, String correo, String usuario, String password){

        if (!datosValidos(nombre, correo, usuario, password)) {
            return null;
        }

        if (buscarPorUsuario(usuario) != null){
            return null;
        }

        Instructor instructor = new Instructor(nombre.trim(), correo.trim(), usuario.trim(), password);
        persons.add(instructor);
        return instructor;
    }

    public Person buscarPorUsuario(String usuario){

        if (usuario == null || usuario.trim().isEmpty()){
            return null;
        }

        for (Person p: persons){
            if (p.getUsuario().equals(usuario.trim())){
                return p;
            }
        }
        return null;
    }

    private boolean datosValidos(String nombre, String correo, String usuario, String password){

        if (nombre == null || nombre.trim().isEmpty()) return false;

        if (correo == null || !correo.contains("@") || !correo.contains(".")) return false;

        if (usuario == null || usuario.trim().isEmpty()) return false;

        if (password == null || password.trim().isEmpty()) return false;

        return true;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }
}
package com.Learnit.model;

import java.util.List;

public class Student extends Person {


    public Student() {
    }

    public Student(String nombre, String correo, String usuario, String password) {
        super(nombre, correo, usuario, password);
    }



    @Override
    public String toString() {
        return "===== Estudiante Registrado =====\n" +
                "Nombre: " + getNombre() + "\n" +
                "Correo: " + getCorreo() + "\n" +
                "Usuario: " + getUsuario() + "\n" +
                "=================================";
    }
}

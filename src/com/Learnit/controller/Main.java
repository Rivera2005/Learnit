package com.Learnit.controller;

import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;
import com.Learnit.service.LoginService;
import com.Learnit.service.PersonService;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        // Crear usuarios basicos
        PersonService personService = new PersonService();
        String nameAdmin = "Admin";
        String correoAdmin = "admin@gmail.com";
        String userAdmin = "Admin";
        String passwordAdmin = "1234";
        personService.registerStudent(nameAdmin, correoAdmin, userAdmin, passwordAdmin);

        String nameStudent = "Luis";
        String correoStudent = "luisn@gmail.com";
        String userStudent = "River";
        String passwordStudent = "1234";
        personService.registerStudent(nameStudent, correoStudent, userStudent, passwordStudent);

        String nameProfesor = "Chema";
        String correoProfesor = "chema@gmail.com";
        String userProfesor = "Chema";
        String passwordProfesor = "1234";
        personService.registerInstructor(nameProfesor, correoProfesor, userProfesor, passwordProfesor);

        System.out.println(personService.getPersons().size());

        // Login
        LoginService loginService = new LoginService(personService);
        Scanner teclado = new Scanner(System.in);
        String userLogin;
        String passLogin;

        System.out.println("¡Bienvenido a Learnit!");
        System.out.print("User: ");
        userLogin = teclado.nextLine();
        System.out.print("Password: ");
        passLogin = teclado.nextLine();

        Person responseLogin = loginService.loginUser(userLogin, passLogin);

        if(responseLogin.getUsuario().equals("Admin")){
            System.out.println("---------- Learnit ----------");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Registrar instructor");
            System.out.println("3. Registrar curso");
            System.out.println("4. Inscribir estudiante en curso");
            System.out.println("5. Ver reporte de cursos");
            System.out.println("6. Salir");
        }
        else if (responseLogin instanceof Student){
            System.out.println("---------- Learnit ----------");
            System.out.println("1. Ver cursos inscritos");
            System.out.println("2. Salir");
        } else if (responseLogin instanceof Instructor) {
            System.out.println("---------- Learnit ----------");
            System.out.println("1. Registrar avance de estudiante");
            System.out.println("2. Salir");
        } else if (responseLogin == null) {
            System.out.println("El usuario no esta registrado");
        }

    }
}
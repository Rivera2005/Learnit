package com.Learnit.controller;

import com.Learnit.model.Course;
import com.Learnit.model.Instructor;
import com.Learnit.model.Person;
import com.Learnit.model.Student;
import com.Learnit.service.LoginService;
import com.Learnit.service.PersonService;
import com.Learnit.service.CourseService;
import com.Learnit.service.EnrollmentService;
import com.Learnit.service.ReportService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        PersonService personService = new PersonService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        ReportService reportService = new ReportService(enrollmentService, courseService);

        // Crear usuarios
        usuariosIniciales(personService);

        LoginService loginService = new LoginService(personService);
        Scanner teclado = new Scanner(System.in);

        boolean sistemaActivo = true;

        while (sistemaActivo) {

            // Bucle para login
            Person responseLogin = null;
            boolean salirLogin = false;

            while (responseLogin == null && !salirLogin) {

                System.out.println("¡Bienvenido a Learnit!");
                System.out.print("User: ");
                String userLogin = teclado.nextLine();

                System.out.print("Password: ");
                String passLogin = teclado.nextLine();

                responseLogin = loginService.loginUser(userLogin, passLogin);

                if (responseLogin == null) {
                    System.out.println("El usuario no esta registrado");
                    System.out.println("¿Desea intentar nuevamente? (s/n)");
                    String opcion = teclado.nextLine();

                    if (opcion.equalsIgnoreCase("n")) {
                        salirLogin = true;
                        sistemaActivo = false;
                        System.out.println("Saliendo de Learnit...");
                    }
                }
            }

            if (!sistemaActivo) {
                break;
            }

            if (responseLogin.getUsuario().equals("Admin")) {

                boolean salirMenu = false;

                while (!salirMenu) {

                    showAdminMenu();
                    System.out.print("Seleccione una opción: ");
                    int opcion = teclado.nextInt();
                    teclado.nextLine(); // limpiar buffer

                    switch (opcion) {

                        case 1:
                            System.out.println("----- Registrar Estudiante -----");
                            registerStudent(personService, teclado);
                            break;

                        case 2:
                            System.out.println("----- Registrar Instructor -----");
                            registerInstructor(personService, teclado);
                            break;

                        case 3:
                            System.out.println("----- Registrar Curso -----");
                            registerCourse(courseService, teclado, personService);
                            break;

                        case 4:
                            System.out.println("Inscribir estudiante");
                            enrollStudent(enrollmentService, courseService, personService, teclado);
                            break;

                        case 5:
                            System.out.println("Ver reporte");
                            reportService.reporteAdmin();
                            break;

                        case 6:
                            salirMenu = true;
                            System.out.println("Cerrando sesión...");
                            break;

                        default:
                            System.out.println("Opción inválida");
                    }
                }

            } else if (responseLogin instanceof Student) {

                boolean salirMenu = false;

                while (!salirMenu) {

                    showStudentMenu();
                    System.out.print("Seleccione una opción: ");
                    int opcion = teclado.nextInt();
                    teclado.nextLine(); // limpiar buffer

                    switch (opcion) {

                        case 1:
                            System.out.println("Ver cursos inscritos");
                            reportService.reporteEstudiante((Student) responseLogin);
                            break;

                        case 2:
                            salirMenu = true;
                            System.out.println("Cerrando sesión...");
                            break;

                        default:
                            System.out.println("Opción inválida");
                    }
                }

            } else if (responseLogin instanceof Instructor) {

                boolean salirMenu = false;

                while (!salirMenu) {

                    showInstructorMenu();
                    System.out.print("Seleccione una opción: ");
                    int opcion = teclado.nextInt();
                    teclado.nextLine(); // limpiar buffer

                    switch (opcion) {

                        case 1:
                            System.out.println("Registrar avance de estudiante");
                            actualizarProgreso(enrollmentService, teclado);
                            break;

                        case 2:
                            salirMenu = true;
                            System.out.println("Cerrando sesión...");
                            break;

                        default:
                            System.out.println("Opción inválida");
                    }
                }
            }
        }

        teclado.close();
    }

    // Usuarios iniciales
    public static void usuariosIniciales(PersonService personService){
        personService.registerStudent("Admin","admin@gmail.com","Admin","1234");
        personService.registerStudent("Luis","luisn@gmail.com","River","1234");
        personService.registerInstructor("Chema","chema@gmail.com","Chema","1234");
    }

    // Menús
    public static void showAdminMenu(){
        System.out.println("---------- Learnit ----------");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar instructor");
        System.out.println("3. Registrar curso");
        System.out.println("4. Inscribir estudiante en curso");
        System.out.println("5. Ver reporte de cursos");
        System.out.println("6. Salir");
    }
    public static void showStudentMenu(){
        System.out.println("---------- Learnit ----------");
        System.out.println("1. Ver cursos inscritos");
        System.out.println("2. Salir");
    }
    public static void showInstructorMenu(){
        System.out.println("---------- Learnit ----------");
        System.out.println("1. Registrar avance de estudiante");
        System.out.println("2. Salir");
    }

    // Forms
    public static void registerStudent(PersonService personService, Scanner teclado){
        System.out.println("Ingrese el nombre del estudiante: ");
        String nameStudent = teclado.nextLine();
        System.out.println("Ingrese le correo del estudiante: ");
        String emailStudent = teclado.nextLine();
        System.out.println("Ingrese el usuario del estudiante: ");
        String userStudent = teclado.nextLine();
        personService.registerStudent(nameStudent, emailStudent, userStudent, userStudent);
        // Se le pasa 2 veces userStudent para dejar que la contraseña sea igual al usuario;
    }
    public static void registerInstructor(PersonService personService, Scanner teclado){
        System.out.println("Ingrese el nombre del estudiante: ");
        String nameInstructor = teclado.nextLine();
        System.out.println("Ingrese le correo del estudiante: ");
        String emailInstructor = teclado.nextLine();
        System.out.println("Ingrese el usuario del estudiante: ");
        String userInstructor = teclado.nextLine();
        personService.registerInstructor(nameInstructor, emailInstructor, userInstructor, userInstructor);
        // Se le pasa 2 veces userStudent para dejar que la contraseña sea igual al usuario;
    }
    public static void registerCourse(CourseService courseService, Scanner teclado, PersonService personService){
        System.out.println("Ingrese el código del curso: ");
        String codigo = teclado.nextLine();
        System.out.println("Ingrese el nombre del curso: ");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese la descripción del curso: ");
        String descripcion = teclado.nextLine();
        System.out.println("Ingrese la duración del curso en horas: ");
        Double duracion = Double.parseDouble(teclado.nextLine());
        System.out.println("Ingrese la materia a la que pertenece el curso: ");
        String materia = teclado.nextLine();
        System.out.println("Ingrese el usuario del instructor del curso: ");
        String usuario = teclado.nextLine();
        Instructor instructor = null;
        for (Person p : personService.getPersons()) {
            if (p instanceof Instructor && p.getUsuario().equals(usuario)) {
                instructor = (Instructor) p;
                break;
            }
        }

        if (instructor == null) {
            System.out.println("Instructor no encontrado");
            return;
        }
        courseService.registerCourse(codigo, nombre, descripcion, duracion, materia, instructor);
    }

    public static void enrollStudent(EnrollmentService enrollmentService, CourseService courseService, PersonService personService, Scanner teclado){
        System.out.print("Ingrese el codigo del curso: ");
        String codigoCurso = teclado.nextLine();
        Course curso = null;
        for (Course c : courseService.getCourses()) {
            if (c.getCodigoCurso().equals(codigoCurso)) {
                curso = c;
                break;
            }
        }
        if (curso == null) {
            System.out.println("Curso no encontrado");
            return;
        }
        System.out.print("Ingrese el usuario del estudiante: ");
        String usuario = teclado.nextLine();
        Student estudiante = null;
        for (Person p : personService.getPersons()) {
            System.out.println(p.getUsuario());
            if (p instanceof Student && p.getUsuario().equals(usuario)) {
                estudiante = (Student) p;
                break;
            }
        }
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado");
            return;
        }

        // esto lo guarda supuestamente en curso
        curso.getEstudiantesInscritos().add(estudiante);
        // y este en enrollment
        System.out.println(curso.toString());
        enrollmentService.enrollStudent(estudiante, curso);
    }
    public static void actualizarProgreso(EnrollmentService enrollmentService, Scanner teclado) {
        System.out.print("Ingrese el usuario del estudiante: ");
        String usuarioEstudiante = teclado.nextLine();
        System.out.print("Ingrese el código del curso: ");
        String codigoCurso = teclado.nextLine();
        System.out.print("Ingrese el nuevo progreso (0 - 100): ");
        Double nuevoProgreso = Double.parseDouble(teclado.nextLine());
        enrollmentService.actualizarProgresos(usuarioEstudiante, codigoCurso, nuevoProgreso);
    }
}
package com.Learnit.controller;

import com.Learnit.model.*;
import com.Learnit.service.LoginService;
import com.Learnit.service.PersonService;
import com.Learnit.service.CourseService;
import com.Learnit.service.EnrollmentService;
import com.Learnit.service.ReportService;
import java.util.Scanner;
import static com.Learnit.controller.UIUtils.*;

public class Main {

    public static void main(String[] args){

        PersonService personService = new PersonService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();
        ReportService reportService = new ReportService(enrollmentService, courseService);

        usuariosIniciales(personService);

        LoginService loginService = new LoginService(personService);
        Scanner teclado = new Scanner(System.in);

        boolean sistemaActivo = true;

        while (sistemaActivo) {

            Person responseLogin = null;
            boolean salirLogin = false;

            while (responseLogin == null && !salirLogin) {

                printHeader("Bienvenido a Learnit");
                System.out.print("User: ");
                String userLogin = teclado.nextLine();

                System.out.print("Password: ");
                String passLogin = teclado.nextLine();

                responseLogin = loginService.loginUser(userLogin, passLogin);

                if (responseLogin == null) {
                    printError("El usuario no esta registrado");
                    System.out.println("¿Desea intentar nuevamente? (s/n)");
                    String opcion = teclado.nextLine();

                    if (opcion.equalsIgnoreCase("n")) {
                        salirLogin = true;
                        sistemaActivo = false;
                        printHeader("Saliendo de Learnit...");
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
                    int opcion = leerEntero(teclado, "Seleccione una opción: ");

                    switch (opcion) {

                        case 1:
                            printHeader("Registrar Estudiante");
                            registerStudent(personService, teclado);
                            break;

                        case 2:
                            printHeader("Registrar Instructor");
                            registerInstructor(personService, teclado);
                            break;

                        case 3:
                            printHeader("Registrar Curso");
                            registerCourse(courseService, teclado, personService);
                            break;

                        case 4:
                            printHeader("Inscribir estudiante");
                            enrollStudent(enrollmentService, courseService, personService, teclado);
                            break;

                        case 5:
                            printHeader("Reporte de cursos");
                            for (String linea : reportService.reporteAdmin()) {
                                System.out.println(linea);
                            }
                            break;

                        case 6:
                            salirMenu = true;
                            printHeader("Cerrando sesión...");
                            break;

                        default:
                            printError("Opción inválida");
                    }
                }

            } else if (responseLogin instanceof Student) {

                boolean salirMenu = false;

                while (!salirMenu) {
                    showStudentMenu();
                    int opcion = leerEntero(teclado, "Seleccione una opción: ");


                    switch (opcion) {

                        case 1:
                            printHeader("Mis cursos");

                            for (String linea : reportService.reporteEstudiante((Student) responseLogin)) {
                                System.out.println(linea);
                            }
                            break;

                        case 2:
                            salirMenu = true;
                            printHeader("Cerrando sesión...");
                            break;

                        default:
                            printError("Opción inválida");
                    }
                }

            } else if (responseLogin instanceof Instructor) {

                boolean salirMenu = false;

                while (!salirMenu) {

                    showInstructorMenu();
                    int opcion = leerEntero(teclado, "Seleccione una opción: ");

                    switch (opcion) {

                        case 1:
                            printHeader("Registrar avance de estudiante");
                            actualizarProgreso(enrollmentService, courseService, (Instructor) responseLogin, teclado);
                            break;

                        case 2:
                            printHeader("Cursos asignados");
                            for (String linea : reportService.reporteInstructor((Instructor) responseLogin)) {
                                System.out.println(linea);
                            }
                            break;

                        case 3:
                            salirMenu = true;
                            printHeader("Cerrando sesión...");
                            break;

                        default:
                           printError("Opción inválida");
                    }
                }
            }
        }

        teclado.close();
    }

    public static void usuariosIniciales(PersonService personService){
        personService.registerStudent("Admin","admin@gmail.com","Admin","1234");
        personService.registerStudent("Luis","luisn@gmail.com","River","1234");
        personService.registerInstructor("Chema","chema@gmail.com","Chema","1234");
    }

    public static void showAdminMenu(){
        printHeader("MENÚ ADMIN");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Registrar instructor");
        System.out.println("3. Registrar curso");
        System.out.println("4. Inscribir estudiante en curso");
        System.out.println("5. Ver reporte de cursos");
        System.out.println("6. Salir");
    }

    public static void showStudentMenu(){
        printHeader("Learnit Estudiantes");
        System.out.println("1. Ver cursos inscritos");
        System.out.println("2. Salir");
    }

    public static void showInstructorMenu(){
        printHeader("Learnit Instructor");
        System.out.println("1. Registrar avance de estudiante");
        System.out.println("2. Ver cursos asignados");
        System.out.println("3. Salir");
    }

    public static void registerStudent(PersonService personService, Scanner teclado){
        String nameStudent = leerTextoNoVacio(teclado, "Ingrese el nombre del estudiante: ");
        String emailStudent = leerTextoNoVacio(teclado, "Ingrese el correo del estudiante (@): ");
        String userStudent = leerTextoNoVacio(teclado, "Ingrese el usuario del estudiante: ");
        Student estudiante_registrado = personService.registerStudent(nameStudent, emailStudent, userStudent, userStudent); // contraseña = usuario
        if (estudiante_registrado != null){
            printSuccess("Estudiante registrado correctamente");
            System.out.println(estudiante_registrado);
        } else {
            printError("Error al registrar usuario");
        }
    }

    public static void registerInstructor(PersonService personService, Scanner teclado){
        String nameInstructor = leerTextoNoVacio(teclado, "Ingrese el nombre del Instructor: ");
        String emailInstructor = leerTextoNoVacio(teclado, "Ingrese le correo del Instructor: ");
        String userInstructor = leerTextoNoVacio(teclado, "Ingrese el usuario del Instructor: ");
        Instructor instructor_registrado = personService.registerInstructor(nameInstructor, emailInstructor, userInstructor, userInstructor); // contraseña = usuario
        if (instructor_registrado != null){
            printSuccess("Instructor registrado correctamente");
            System.out.println(instructor_registrado);
        } else {
            printError("Error al registrar instructor");
        }
    }

    public static void registerCourse(CourseService courseService, Scanner teclado, PersonService personService){
        String codigo = leerTextoNoVacio(teclado, "Ingrese el código del curso: ");
        String nombre = leerTextoNoVacio(teclado, "Ingrese el nombre del curso: ");
        String descripcion = leerTextoNoVacio(teclado, "Ingrese la descripción del curso: ");
        double duracion;
        while (true){
            try{
                System.out.println("Ingrese duración del curso en horas: ");
                duracion = Double.parseDouble(teclado.nextLine());
                break;
            } catch (NumberFormatException e){
                printError("Debe ingresar un número valido");
            }
        }
        String materia = leerTextoNoVacio(teclado, "Ingrese la materia a la que pertenece el curso: ");
        String usuario = leerTextoNoVacio(teclado, "Ingrese el usuario del instructor:");
        Instructor instructor = obtenerInstructor(personService, usuario);
        if (instructor == null){
            printError("Instructor no encontrado");
            return;
        }

        Course curso_registrado = courseService.registerCourse(codigo, nombre, descripcion, duracion, materia, instructor);

        if (curso_registrado != null){
            printSuccess("Curso creado correctamente");
            System.out.println(curso_registrado);
        }else{
            printError("Error al inscribir el curso");
        }
    }

    public static void enrollStudent(EnrollmentService enrollmentService, CourseService courseService, PersonService personService, Scanner teclado){
        String codigoCurso = leerTextoNoVacio(teclado, "Ingrese el codigo del curso: ");
        Course curso = courseService.buscarPorCodigo(codigoCurso);

        if (curso == null) {
            printError("Curso no encontrado");
            return;
        }

        String usuario = leerTextoNoVacio(teclado, "Ingrese el usuario del estudiante: ");
        Student estudiante = obtenerStudent(personService, usuario);
        if (estudiante == null){
            printError("Estudiante no encontrado");
            return;
        }
        Enrollment inscripcion_EstudianteCurso = enrollmentService.enrollStudent(estudiante, curso);
        if (inscripcion_EstudianteCurso != null){
            printSuccess("Inscripción realizada correctamente.");
            System.out.println(inscripcion_EstudianteCurso);
        } else {
            printError("Error al inscribir el estudiante al curso.");
        }
    }

    public static void actualizarProgreso(EnrollmentService enrollmentService, CourseService courseService, Instructor instructor, Scanner teclado) {
        String usuarioEstudiante = leerTextoNoVacio(teclado,"Ingrese el usuario del estudiante: ");
        String codigoCurso = leerTextoNoVacio(teclado, "Ingrese el código del curso: ");
        Course curso = courseService.buscarPorCodigo(codigoCurso);

        if (curso == null) {
            printError("Curso no encontrado");
            return;
        }

        if (!curso.getInstructor().getUsuario().equals(instructor.getUsuario())) {
            printError("No puedes modificar este curso (no está asignado a ti)");
            return;
        }

        double nuevoProgreso;
        while (true){
            try{
                System.out.println("Ingrese el nuevo progreso (0 - 100): ");
                nuevoProgreso = Double.parseDouble(teclado.nextLine());
                break;
            } catch (NumberFormatException e){
                printError("Debe ingresar un número valido");
            }
        }

        Enrollment inscripcion = enrollmentService.buscarInscripcion(usuarioEstudiante, codigoCurso);
        if (inscripcion == null){
            printError("No se encontró la inscripción");
            return;
        }
        Double progresoAnterior = (inscripcion != null) ? inscripcion.getProgreso() : null;
        Enrollment progreso_actualizado = enrollmentService.actualizarProgresos(usuarioEstudiante, codigoCurso, nuevoProgreso);
        if(progreso_actualizado != null){
            printSuccess("Progreso actualizado correctamente.");
            printSubHeader("Detalle");
            System.out.println("Antes: " + progresoAnterior + "%");
            System.out.println("Ahora: " + progreso_actualizado.getProgreso() + "%\n");
            printLine();
            System.out.println(progreso_actualizado);

            if (progreso_actualizado.getProgreso() >= 100){
                System.out.println("¡El estudiante ha completado el curso!");
            }
        }else{
            printError("Error al actualizar progreso.");
        }
    }

    public static String leerTextoNoVacio(Scanner teclado, String mensaje){
        while (true){
            System.out.println(mensaje);
            String input = teclado.nextLine().trim();

            if (!input.isEmpty()){
                return input;
            }

            printError("Este campo no puede estar vacío.");
        }
    }

    public static int leerEntero(Scanner teclado, String mensaje){
        while (true){
            try {
                System.out.println(mensaje);
                return Integer.parseInt(teclado.nextLine());
            }catch (NumberFormatException e){
                printError("Debe ingresar un número valido");
            }
        }
    }

    public static Student obtenerStudent(PersonService personService, String usuario){
        Person p = personService.buscarPorUsuario(usuario);
        if (!(p instanceof Student)){
            return null;
        }
        return (Student) p;
    }

    public static Instructor obtenerInstructor(PersonService personService, String usuario){
        Person p = personService.buscarPorUsuario(usuario);
        if (!(p instanceof Instructor)){
            return null;
        }
        return (Instructor) p;
    }
}
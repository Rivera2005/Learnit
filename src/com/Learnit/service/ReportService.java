package com.Learnit.service;

import com.Learnit.model.Course;
import com.Learnit.model.Enrollment;
import com.Learnit.model.Student;

import java.util.ArrayList;

public class ReportService {

    private EnrollmentService enrollmentService;
    private CourseService courseService;

    public ReportService(EnrollmentService enrollmentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;}

    public void reporteAdmin() {
        ArrayList<Course> cursos = courseService.getCourses();
        ArrayList<Enrollment> inscripciones = enrollmentService.getEnrollments();

        System.out.println("Reporte de cursos");

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;}

        for (Course curso : cursos) {
            System.out.println("Curso: " + curso.getNombre());
            System.out.println("Inscritos: " + curso.getEstudiantesInscritos().size());

            for (Enrollment e : inscripciones) {
                if (e.getCurso().getCodigoCurso().equals(curso.getCodigoCurso())) {
                    System.out.println(e.getEstudiante().getNombre() + " - Progreso: " + e.getProgreso() + "%");}
            }
        }
    }

    public void reporteEstudiante(Student estudiante) {
        ArrayList<Enrollment> inscripciones = enrollmentService.getEnrollments();

        System.out.println("Reporte de cursos:");

        boolean tieneCursos = false;
        for (Enrollment e : inscripciones) {
            if (e.getEstudiante().getUsuario().equals(estudiante.getUsuario())) {
                tieneCursos = true;
                System.out.println("Curso: " + e.getCurso().getNombre() + " Progreso: " + e.getProgreso() + "%");}
        }

        if (!tieneCursos) {
            System.out.println("No hay cursos inscritos.");}
    }
}

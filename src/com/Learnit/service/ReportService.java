package com.Learnit.service;

import com.Learnit.model.Course;
import com.Learnit.model.Enrollment;
import com.Learnit.model.Instructor;
import com.Learnit.model.Student;

import java.util.ArrayList;

public class ReportService {

    private EnrollmentService enrollmentService;
    private CourseService courseService;

    public ReportService(EnrollmentService enrollmentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    public ArrayList<String> reporteAdmin() {

        ArrayList<String> reporte = new ArrayList<>();

        ArrayList<Course> cursos = courseService.getCourses();
        ArrayList<Enrollment> inscripciones = enrollmentService.getEnrollments();

        if (cursos.isEmpty()) {
            reporte.add("No hay cursos registrados.");
            return reporte;
        }

        for (Course curso : cursos) {
            reporte.add("\n===== CURSO =====");
            reporte.add("Nombre: " + curso.getNombre());

            int contador = 0;
            for (Enrollment e : inscripciones) {
                if (e.getCurso().getCodigoCurso().equals(curso.getCodigoCurso())) {
                    contador++;
                }
            }
            reporte.add("Inscritos: " + contador);

            if(contador == 0){
                reporte.add("No hay estudiantes inscritos.");
            }else{
                reporte.add("Estudiantes:");
                for (Enrollment e : inscripciones) {
                    if (e.getCurso().getCodigoCurso().equals(curso.getCodigoCurso())) {
                        reporte.add(" - " + e.getEstudiante().getNombre() +
                                " | Progreso: " + e.getProgreso() + "%");
                    }
                }
            }

            reporte.add("========================");

        }
        return reporte;
    }

    public ArrayList<String> reporteEstudiante(Student estudiante) {
        ArrayList<String> reporte = new ArrayList<>();
        ArrayList<Enrollment> inscripciones = enrollmentService.getEnrollments();

        reporte.add("\n===== MIS CURSOS =====");


        boolean tieneCursos = false;
        for (Enrollment e : inscripciones) {
            if (e.getEstudiante().getUsuario().equals(estudiante.getUsuario())) {
                tieneCursos = true;
                reporte.add(" - " + e.getCurso().getNombre() +
                        " | Progreso: " + e.getProgreso() + "%");
            }
        }

        if (!tieneCursos) {
            reporte.add("No hay cursos inscritos.");
        }

        reporte.add("========================");

        return reporte;
    }

    public ArrayList<String> reporteInstructor(Instructor instructor) {

        ArrayList<String> reporte = new ArrayList<>();

        reporte.add("\n===== MIS CURSOS (INSTRUCTOR) =====");

        if (instructor.getCursosAsignados() == null || instructor.getCursosAsignados().isEmpty()) {
            reporte.add("No tienes cursos asignados.");
        } else {
            for (Course c : instructor.getCursosAsignados()) {
                reporte.add(" - " + c.getNombre() +
                        " (" + c.getCodigoCurso() + ")");
            }
        }

        reporte.add("========================");

        return reporte;
    }

}



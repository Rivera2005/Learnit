package com.Learnit.service;

import com.Learnit.model.*;

import java.util.ArrayList;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private int contador = 1;

    public void enrollStudent(Student estudiante, Course curso){
        Enrollment enrollment = new Enrollment(contador++, estudiante, curso, 0.0);
        enrollments.add(enrollment);
        System.out.println("Inscripción realizada correctamente :)");
    }
    public void actualizarProgresos( String usuariosEstudiante, String codigoCurse,Double nuevoProgreso){
        if (nuevoProgreso < 0 || nuevoProgreso > 100){
            System.out.println("Error: el progreso debe ser un valor entre 0 y 100,");
            return;
        }
        for (Enrollment enrollment : enrollments){
            boolean mismoEstudiante = enrollment.getEstudiante().getUsuario().equals(usuariosEstudiante);
            boolean mismoCuros = enrollment.getCurso().getCodigoCurso().equals(codigoCurse);

            if (mismoEstudiante && mismoCuros){
                Double progeroAnterior = enrollment.getProgreso();
                enrollment.setProgreso(nuevoProgreso);
                System.out.println("Progreso actualozadp correctamente :)");
                System.out.println("Estudiante ; " + enrollment.getEstudiante().getNombre());
                System.out.println("Curso : " + enrollment.getCurso().getNombre());
                System.out.println("Progreso : " + progeroAnterior + "% -> " + nuevoProgreso + "%");
                if (nuevoProgreso >= 100){
                    System.out.println("Muy bien " + enrollment.getEstudiante().getNombre() + "ha completado todo el curso ;))");
                }
                return;
            }
        }
        System.out.println("No se encontro inscripción para el estudiante " + usuariosEstudiante + " en el curso " + codigoCurse +".");
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }
}


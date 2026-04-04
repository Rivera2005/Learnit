package com.Learnit.service;

import com.Learnit.model.*;

import java.util.ArrayList;
import java.util.EventObject;

public class EnrollmentService {

    private ArrayList<Enrollment> enrollments = new ArrayList<>();

    public boolean yaEstaInscrito(Student student, Course course){
        for (Enrollment e: enrollments){
            if(e.getEstudiante().equals(student) && e.getCurso().equals(course)){
                return true;
            }
        }
        return false;
    }

    private int contador = 1;

    public Enrollment enrollStudent(Student estudiante, Course curso){
        if (yaEstaInscrito(estudiante, curso)){
            return null;
        }
        if (estudiante == null || curso == null) return null;
        Enrollment enrollment = new Enrollment(contador++, estudiante, curso, 0.0);
        enrollments.add(enrollment);
        return enrollment;
    }

    public Enrollment actualizarProgresos( String usuariosEstudiante, String codigoCurse,Double nuevoProgreso){
        if (usuariosEstudiante == null || codigoCurse == null || nuevoProgreso == null) return null;

        if (nuevoProgreso < 0 || nuevoProgreso > 100){
            return null;
        }

        Enrollment enrollment = buscarInscripcion(usuariosEstudiante, codigoCurse);

        if (enrollment != null){
            enrollment.setProgreso(nuevoProgreso);
        }

        return enrollment;
    }

    public Enrollment buscarInscripcion(String usuario, String codigoCurso){
        for (Enrollment e : enrollments){
            boolean mismoEstudiante = e.getEstudiante().getUsuario().equals(usuario);
            boolean mismoCurso = e.getCurso().getCodigoCurso().equals(codigoCurso);

            if (mismoEstudiante && mismoCurso){
                return e;
            }
        }
        return null;
    }


    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }


}


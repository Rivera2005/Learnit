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


    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }
}
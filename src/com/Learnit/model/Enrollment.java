package com.Learnit.model;

public class Enrollment {
    private int id_inscripcion;
    private Student estudiante;
    private Course curso;
    private Double progreso;

    public Enrollment() {
    }

    public Enrollment(int id_inscripcion, Student estudiante, Course curso, Double progreso) {
        this.id_inscripcion = id_inscripcion;
        this.estudiante = estudiante;
        this.curso = curso;
        this.progreso = progreso;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Student getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Student estudiante) {
        this.estudiante = estudiante;
    }

    public Course getCurso() {
        return curso;
    }

    public void setCurso(Course curso) {
        this.curso = curso;
    }

    public Double getProgreso() {
        return progreso;
    }

    public void setProgreso(Double progreso) {
        this.progreso = progreso;
    }

    @Override
    public String toString() {
        return "===== INSCRIPCIÓN =====\n" +
                "Estudiante: " + estudiante.getNombre() + "\n" +
                "Curso: " + curso.getNombre() + "\n" +
                "Código: " + curso.getCodigoCurso() + "\n" +
                "Progreso: " + progreso + "%\n" +
                "========================";
    }
}

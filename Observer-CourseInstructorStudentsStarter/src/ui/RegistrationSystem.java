package ui;


import model.Course;
import model.Instructor;
import model.OnlineStudent;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();

    public RegistrationSystem(){
        Instructor gregor = new Instructor ("Gregor");
        Instructor elisa = new Instructor("Elisa");
        Instructor reid = new Instructor ("Reid");
        instructors.add(gregor);
        instructors.add(elisa);
        instructors.add(reid);

        Course howToCode = new Course("How to Code");
        howToCode.addInstructor(gregor);
        courses.add(howToCode);

        Course softwareConstruction = new Course("Software Construction");
        softwareConstruction.addInstructor(elisa);
        courses.add(softwareConstruction);

        Course softwareEng = new Course("Software Engineering");
        softwareEng.addInstructor(reid);
        courses.add(softwareEng);

        registerStudent(new OnlineStudent(1, "Erika"), softwareConstruction);
        registerStudent(new OnlineStudent(2, "James"), softwareConstruction);
        softwareConstruction.printEnrolled();

        elisa.prepareModule("Observer Pattern");
    }

    public void registerStudent(Student s, Course c) {
        c.addStudent(s);
    }

    public static void main(String[] args) {
        new RegistrationSystem();
    }


}
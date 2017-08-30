package model;


import java.util.ArrayList;
import java.util.List;

public class Course {
    private String courseName;
    private Instructor instructor;
    private List<Student> students = new ArrayList<>();
    private List<LectureModule> lectureModules = new ArrayList<>();

    public Course(String courseName){
        this.courseName = courseName;
    }

    public void addInstructor(Instructor instructor) {
        this.instructor = instructor;
        instructor.assignCourse(this);
    }

    public void addStudent(Student student) {
        if(!students.contains(student)) {
            students.add(student);
            student.addCourse(this);
        }
    }

    public void printEnrolled(){
        for (Student s : students){
            s.printRecord();
        }
    }

    public void addLectureModule(LectureModule lectureModule) {
        lectureModules.add(lectureModule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return courseName.hashCode();
    }


}

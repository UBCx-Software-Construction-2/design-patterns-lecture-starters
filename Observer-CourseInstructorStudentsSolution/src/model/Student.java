package model;

import observer.LectureObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Student implements Teachable, LectureObserver {

    private String name;
    private int id;
    private List<Course> courses = new ArrayList<>();

    public Student(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void addCourse(Course c){
        if(!courses.contains(c)) {
            courses.add(c);
            c.addStudent(this);
        }
    }

    @Override
    public void update(LectureModule lectureModule){
        System.out.println("Prof has posted lecture module "+lectureModule);
    }

    public void printRecord(){
        System.out.println(id + ":" + name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }


}
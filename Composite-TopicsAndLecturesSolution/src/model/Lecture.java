package model;


public class Lecture extends Module {

    private String name;

    public Lecture(String s) {
        this.name = s;
    }

    public void display(String indentLevel) {
        System.out.println(indentLevel+name);
    }


}
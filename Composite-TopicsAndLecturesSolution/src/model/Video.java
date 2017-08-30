package model;

public class Video extends Module {

    private String name;

    public Video(String s) {
        this.name = s;
    }

    public void display(String indentLevel) {
        System.out.println(indentLevel + name);
    }


}
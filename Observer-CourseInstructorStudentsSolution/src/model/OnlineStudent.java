package model;


public class OnlineStudent extends Student {


    public OnlineStudent(int i, String name) {
        super(i, name);
    }

    public void learn(){
        System.out.println("I will now go online and learn things!");
    }


}
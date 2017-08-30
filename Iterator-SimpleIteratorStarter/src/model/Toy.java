package model;

public class Toy {
    private String name;

    public Toy(String name) {
        this.name =  name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
}

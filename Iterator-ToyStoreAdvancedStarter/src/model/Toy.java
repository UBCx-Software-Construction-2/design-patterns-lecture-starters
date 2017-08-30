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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Toy)) return false;

        Toy toy = (Toy) o;

        return name.equals(toy.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

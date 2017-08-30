package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Inventory implements Iterable<Toy>{

    private List<Toy> toys = new ArrayList<>();
    private ArrayList<String> log = new ArrayList<>();

    // getters
    public int getSize() { return toys.size(); }
    public Toy getToy(int i) { return toys.get(i); }

    public void add(Toy t) {
        toys.add(t);
        log.add("New toy added: "+t);
    }

    public void remove(Toy t){
        toys.remove(t);
        log.add("Toy "+t+" removed");
    }

    public void printQueryStats(){
        for(String logEntry : log){
            System.out.println(logEntry);
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Toy> iterator() {
        return toys.iterator();
    }


}
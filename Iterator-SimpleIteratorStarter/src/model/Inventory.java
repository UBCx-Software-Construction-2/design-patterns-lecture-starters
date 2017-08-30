package model;

import java.util.ArrayList;
import java.util.List;


public class Inventory {

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


}
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Inventory implements Iterable<Toy>{

    private Collection<Toy> toys = new ArrayList<>();
    private Collection<Toy> backlog = new ArrayList<>();
    private ArrayList<String> log = new ArrayList<>();

    // getters
    public int getSize() { return toys.size(); }

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


    public void moveToBacklog(Toy toy) {
        toys.remove(toy);
        backlog.add(toy);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Toy> iterator() {
        return new InventoryIterator();
    }

    private class InventoryIterator implements Iterator<Toy> {
        Iterator toysIterator = toys.iterator();
        Iterator backlogIterator = backlog.iterator();

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return toysIterator.hasNext() || backlogIterator.hasNext();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Toy next() {
            Toy toy;
            if (toysIterator.hasNext()){
                toy = (Toy) toysIterator.next();
            }
            else {
                toy = (Toy) backlogIterator.next();
            }
            return toy;
        }
    }


}
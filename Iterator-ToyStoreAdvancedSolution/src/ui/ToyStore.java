package ui;

import model.Toy;
import model.Inventory;

public class ToyStore {

    Inventory inventory = new Inventory();

    public void displayInventory(){
        System.out.println("Why hello! May I interest you in...");
        for(Toy t : inventory){
            tryToSell(t);
        }
    }

    public void addToy(Toy t){
        inventory.add(t);
    }

    public static void main(String[] args) {
        ToyStore allNaturalToys = new ToyStore();
        allNaturalToys.addToy(new Toy("blocks"));
        allNaturalToys.addToy(new Toy("sticks"));
        allNaturalToys.addToy(new Toy("rocks"));
        allNaturalToys.addToy(new Toy("wool"));
        allNaturalToys.unstock(new Toy("blocks"));
        allNaturalToys.displayInventory();
        allNaturalToys.analyzeShopping();
    }

    private void unstock(Toy toy) {
        inventory.moveToBacklog(toy);
    }

    private void analyzeShopping() {
        inventory.printQueryStats();
    }

    private void tryToSell(Toy t){
        System.out.println("lovely "+t+"?");
    }


}
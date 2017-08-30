package model;

import java.util.ArrayList;
import java.util.List;

public class Topic extends Module {

    private List<Module> modules = new ArrayList<>();
    private String name;

    public Topic (String name) {
        this.name = name;
    }

    public void addModule(Module m) {
        modules.add(m);
    }

    public void display(String indentLevel) {
        System.out.println(indentLevel+name);
        for (Module module : modules){
            module.display(indentLevel+indentLevel);
        }
    }


}
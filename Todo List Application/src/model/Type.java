package model;

import ui.TaskMonitor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

public class Type {
    private String name;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int num;

    //EFFECT: Creates a new type with a name and empty list of tasks and the size of the tasklist
    public Type(String type){
        this.name = type;
        num = tasks.size();

    }

    //EFFECT: Gets the type name
    public String getName(){
        return name;
    }

    public ArrayList<Task> getListofTasks(){
        return tasks;
    }

    public void addTask(Task t){
        if (!tasks.contains(t)) {
            tasks.add(t);
            num += 1;
        }
        else
            System.out.println("You already have that task in the list");
    }

    public void removeTask(Task t){
        if (tasks.contains(t)) {
            tasks.remove(t);
            num -= 1;
        }
    }

    public int getNumberofTasks(){
        return num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

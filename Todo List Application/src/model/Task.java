package model;

import java.util.*;

public abstract class Task {
    protected String name;
    protected String priority;
    protected Type type;
    protected String dueDate;
    //protected List<Observer> observers;

    public Task(String t, String n, String p){
        type = new Type(t);
        this.name = n;
        this.priority = p;
        //observers = new ArrayList<>();
        //this.dueDate = d;
    }

    //MODIFIES: this
    //EFFECT: Set the name of the task
    public abstract void settaskName(String s);

    //MODIFIES: this
    //EFFECT: Set the priority of the task
    public void settaskPriority(String s){
        this.priority = s;
    }

    //MODIFIES: this
    //EFFECT: Set the Due Date of the task
    public void settaskdueDate(String s){
        this.dueDate = s;
    }

    //EFFECT: Get the name of the task
    public String gettaskName(){
        return name;
    }

    //EFFECT: Get the priority of the task
    public String gettaskPriority(){
        return priority;
    }

    //EFFECT: Get the Due Date of the task
    public String gettaskdueDate(){
        return dueDate;
    }

    public Type gettaskType(){
        return type;
    }

 /*   public List<Observer> getObservers(){
        return observers;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}

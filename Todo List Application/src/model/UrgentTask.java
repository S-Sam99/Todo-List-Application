package model;

public class UrgentTask extends Task {
    public UrgentTask(String n, String p) {
        super("Urgent",n, "ASAP");
        this.name = n.toUpperCase();
    }

    @Override
    public void settaskName(String s){
        this.name = s.toUpperCase();
    }

    public void settaskPriority(String s){ this.priority = "ASAP"; }
}

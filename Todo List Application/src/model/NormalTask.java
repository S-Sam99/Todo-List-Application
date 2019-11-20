package model;

public class NormalTask extends Task {
    public NormalTask(String n, String p) {
        super("Normal", n, p);
    }

    public void settaskName(String s){
        this.name = s;
    }

}

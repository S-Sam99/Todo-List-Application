package model;

public class SecretTask extends Task{
    public SecretTask(String n, String p){
        super("Secret",n, p);
        if (n.endsWith("...shhhh"))
            this.name = n;
        else
            settaskName(n);
    }

    @Override
    public void settaskName(String s){
        if (s.endsWith("...shhhh"))
            this.name = s;
        else
            this.name = s + "...shhhh";
    }

}

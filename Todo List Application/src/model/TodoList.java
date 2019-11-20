package model;

import exceptions.NoFilesFound;
import exceptions.TooManyTasks;
import interfaces.Loadable;
import interfaces.Saveable;
import ui.TaskMonitor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TodoList extends Observable implements Saveable, Loadable {
    private Map<String, Type> TypesofTasks;
    private Type t1 = new Type("Normal");
    private Type t2 = new Type("Secret");
    private Type t3 = new Type("Urgent");
    Type type;
    private Task t;
    private String response = "";
    private String fullList = "";
    private int num;


    public TodoList(){
        TypesofTasks = new HashMap<>();
        TypesofTasks.put("Normal", t1);
        TypesofTasks.put("Secret", t2);
        TypesofTasks.put("Urgent", t3);
        addObserver(new TaskMonitor());

    }

    public Task addTask(String input,String name,String priority) throws TooManyTasks {
        if ((t1.getListofTasks().size() >= 5) || (t2.getListofTasks().size() >= 5) || (t3.getListofTasks().size() >= 5)) {
            throw new TooManyTasks();
        }
        if (input.equals("Normal"))
            t = new NormalTask(name, priority);
        else if (input.equals("Secret"))
            t = new SecretTask(name,priority);
        else
            t = new UrgentTask(name,priority);
        type = TypesofTasks.get(t.gettaskType().getName());
        int remembered = type.getNumberofTasks();
        type.addTask(t);
        if (type.getNumberofTasks() > remembered) {
            setChanged();
            notifyObservers(t);
        }
        return t;
    }

    public String showTasks(Type t){
       String totalTasks = "";
       totalTasks+=("[" + t.getName() + " Tasks] " + t.getNumberofTasks() + " task(s) outstanding" + "\n");
        if (t.getListofTasks().size() > 0) {
            for (int i = 0; i < t.getListofTasks().size(); i++) {
                totalTasks+=("[" + i + "] " + t.getListofTasks().get(i).gettaskName() + ", priority: " + t.getListofTasks().get(i).gettaskPriority() + "\n");
            }
            totalTasks+=("--------------------------------------------" + "\n");
        }
        else {
            totalTasks+=("No Tasks" + "\n");
            totalTasks+=("--------------------------------------------" + "\n");
        }
        return totalTasks;
    }

    public String lookatTasks(){
        fullList+=("You have the following tasks:" + "\n");
        fullList+=showTasks(t1);
        fullList+=showTasks(t2);
        fullList+=showTasks(t3);
        return fullList;
    }

    public Type getType(String s) {
        if (s.equals("n"))
            return TypesofTasks.get("Normal");
        if (s.equals("s"))
            return TypesofTasks.get("Secret");
        else
            return TypesofTasks.get("Urgent");
    }

    public void removeTaskinList(String s, String type) {
        Type t = TypesofTasks.get(type);
        ArrayList<Task> l = t.getListofTasks();
        if (l.size() > 0) {
            for (int i = 0; i < l.size(); i++) {
                if ((Integer.toString(i)).equals(s) || (l.get(i).gettaskName()).equals(s)) {
                    Task temp = l.get(i);
                    response += ("You successfully removed " + l.get(i).gettaskName() + " from your list");
                    t.removeTask(temp);
                }
            }
        } else
            response += ("Could not find that number or item in the list you are looking for");
    }

    public Map<String,Type> returnTypesOfTasks(){
        return TypesofTasks;
    }

    public void clearList(){
        fullList = "";
    }

    public String updateResponse(){
        String temp = response;
        response = "";
        return temp;
    }

    public void setResponse(String s){
        response = s;
    }

    public void setNum(int n){
        num = n;
    }

    public int getNum(){
        return num;
    }

    @Override
    public void load(String s) throws AccessDeniedException, IOException {
        int tempNum = 0;
        File temp = new File(s);
        if (!temp.exists())
            throw new AccessDeniedException(s);
        List<String> tasks = Files.readAllLines(Paths.get(s));
        for (String task: tasks) {
            ArrayList<String> partsofTask = splitOnSpace(task);
            String string = "";
            for (int k=1; k < partsofTask.size()-1; k++){
                string+=(partsofTask.get(k) + " ");
                if (k==partsofTask.size()-2)
                    string+=partsofTask.get(k);
            }
            if (partsofTask.get(0).equals("Normal"))
                t = new NormalTask(string, partsofTask.get(partsofTask.size()-1));
            else if (partsofTask.get(0).equals("Secret"))
                t = new SecretTask(string, partsofTask.get(partsofTask.size()-1));
            else
                t = new UrgentTask(string, partsofTask.get(partsofTask.size()-1));
            Type type = returnTypesOfTasks().get(t.gettaskType().getName());
            type.addTask(t);
            tempNum+=1;
            setNum(tempNum);
        }
    }

    @Override
    public void saveTasks(String s,TodoList todo) throws NoFilesFound, IOException {
        File temp = new File(s);
        if (!temp.exists())
            throw new NoFilesFound();
        PrintWriter writer = new PrintWriter(s,"UTF-8");
        for(String name: todo.returnTypesOfTasks().keySet())
            saveType(name,s,writer);
        writer.close();
    }

    public void saveType(String name, String s, PrintWriter w) throws IOException{
        Type typeList = returnTypesOfTasks().get(name);
        //PrintWriter writer = new PrintWriter(s,"UTF-8");
        for (Task t: typeList.getListofTasks()){
            w.write(t.gettaskType().getName() + " ");
            w.write(t.gettaskName() + " ");
            w.write(t.gettaskPriority() + " ");
            w.println("");
        }
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}

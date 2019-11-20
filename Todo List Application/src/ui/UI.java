package ui;

import exceptions.TooManyTasks;
import exceptions.NoFilesFound;
import interfaces.Loadable;
import interfaces.Saveable;
import model.*;

import java.io.*;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class UI implements Saveable, Loadable {
    private String s1 = "[n] for normal task";
    private String s2 = "[s] for secret task";
    private String s3 = "[u] for urgent task";
    private String input = "";
    private Task t;
    private ArrayList<String> OptionList;
    private TodoList Todo;

    public UI() throws IOException{
        Todo = new TodoList();
        OptionList = new ArrayList<>();
        OptionList.add(s1);
        OptionList.add(s2);
        OptionList.add(s3);
        try {
            load("savedTasks");
        } catch (AccessDeniedException e){
            System.out.println("We could not find any previously saved tasks");
        }
    }
    //EFFECTS: Checks the user input if [1]: adds a task, [2]: removes a task, [3]: looks at the current tasks,
    //         [quit]: quits the application, otherwise, repeat
    public void cycle() throws IOException {
        repeatasnecessary();
        if (input.equals("1")) {
            try {
                Option1();
            } catch (TooManyTasks e) {
                System.out.println("You have too many uncompleted tasks!!!");
                input = "";
            } finally {
                cycle();
            }
        } else if (input.equals("2")) {
            Option2();
            input = "";
            cycle();
        } else if (input.equals("3")) {
            Todo.lookatTasks();
            input = "";
            cycle();
        } else if (input.equals("quit")) {
            try {
                saveTasks("savedTasks", Todo);
            } catch (NoFilesFound e) {
                System.out.println("There was not a file to save to");
            }
        }
    }

    //REQUIRED: TypesofTasks is not null
    //MODIFES: TypesofTasks
    //EFFECT: Adds a task to the TypesofTasks
    public void Option1() throws TooManyTasks, IOException {
        while (!input.equals("back")) {
            String priority = "";
            System.out.println("Please enter the type of task you would like to add or type [back] to go back");
            showTypeList();
            input = scannextLine();
            if (input.equals("back"))
                break;
            else {
                while (!equalType(input)) {
                    System.out.println("That is not a valid type");
                    input = scannextLine();
                }
                System.out.println("What is the name of the task you would like to add?");
                String name = scannextLine();
                if (input.equals("n") || input.equals("s")) {
                    System.out.println("Please enter the task priority (low, med, or high)");
                    priority = scannextLine();
                    while (!equalPriority(priority)) {
                        System.out.println("That is not a valid priority");
                        priority = scannextLine();
                    }
                }
                Todo.addTask(input,name,priority);
            }
        }
    }

    //REQUIRED: taskList is not null
    //MODIFIES: taskList
    //EFFECT: Removes a task from the list, otherwise, do nothing
    public void Option2() throws IOException {
        if (noTasks(Todo))
            System.out.println("There are no tasks to delete");
        else {
            while (!input.equals("back")) {
                Todo.lookatTasks();
                System.out.println("Enter the type of task you would like to remove or [back] to go back");
                showTypeList();
                input = scannextLine();
                if (input.equals("back"))
                    break;
                while (!equalType(input)) {
                    System.out.println("Please enter a valid option");
                    input = scannextLine();
                }
                String name = input;
                if (checkifEmpty(Todo.getType(input))) {
                    System.out.println("There is nothing to remove in this list");
                } else {
                    System.out.println("Please enter the task number or name to delete");
                    input = scannextLine();
                    Todo.removeTaskinList(input, name);
                }
            }
        }
        input = "";
    }
    
    private boolean equalCriteria(String s){
        if (s.equals("1") || s.equals("2") || s.equals("3") || s.equals("quit"))
            return true;
        return false;
    }

    private boolean equalType(String s){
        if (s.equals("n") || s.equals("s") || s.equals("u"))
            return true;
        return false;
    }

    public boolean equalPriority(String s){
        if (s.equals("low") || s.equals("med") || s.equals("high"))
            return true;
        return false;
    }
    
    private void repeatasnecessary() {
        System.out.println("What would you like to do today?");
        System.out.println("[1] Add a task");
        System.out.println("[2] Remove a task");
        System.out.println("[3] Look at tasks");
        input = scannextLine();
        while (!equalCriteria(input)) {
            System.out.println("Please enter a valid option");
            input = scannextLine();
        }
    }

    private void showTypeList(){
        for (int i=0; i<OptionList.size(); i++){
            System.out.println(OptionList.get(i));
        }
    }

    private boolean checkifEmpty(Type t){
        if (t.getListofTasks().isEmpty())
            return true;
        return false;
    }

    public boolean noTasks(TodoList todo){
        for(String name: todo.returnTypesOfTasks().keySet()){
            ArrayList<Task> tasks = todo.returnTypesOfTasks().get(name).getListofTasks();
            if (tasks.size()>0)
                return false;
        }
        return true;
    }
    
    //MODIFIES; this
    //EFFECT: Scans the next line for user input
    public String scannextLine() {
        Scanner reader = new Scanner(System.in);
        String s = reader.nextLine();
        return s;
    }

    @Override
    public void load(String s) throws AccessDeniedException, IOException {
        File temp = new File(s);
        if (!temp.exists())
            throw new AccessDeniedException(s);
        List<String> tasks = Files.readAllLines(Paths.get(s));
        for (String task: tasks) {
            ArrayList<String> partsofTask = splitOnSpace(task);
            if (partsofTask.get(0).equals("Normal"))
                t = new NormalTask(partsofTask.get(1), partsofTask.get(2));
            else if (partsofTask.get(0).equals("Secret"))
                t = new SecretTask(partsofTask.get(1), partsofTask.get(2));
            else
                t = new UrgentTask(partsofTask.get(1), partsofTask.get(2));
            Type type = Todo.returnTypesOfTasks().get(t.gettaskType().getName());
            type.addTask(t);
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
        Type typeList = Todo.returnTypesOfTasks().get(name);
        //PrintWriter writer = new PrintWriter(s,"UTF-8");
        for (Task t: typeList.getListofTasks()){
            w.write(t.gettaskType().getName() + " ");
            w.write(t.gettaskName() + " ");
            w.write(t.gettaskPriority() + " ");
            w.println("");
        }
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}

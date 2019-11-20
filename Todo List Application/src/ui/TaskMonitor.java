package ui;

import model.Task;
import model.TodoList;
import model.Type;

import java.util.Observable;
import java.util.Observer;

public class TaskMonitor implements Observer {
    int num = 0;

    @Override
    public void update(Observable o, Object a){
        TodoList t = (TodoList) o;
        if (num==0)
            num = t.getNum();
        Task task = (Task) a;
        String temp = "";
        num+=1;
        temp+=("You have added the task " + task.gettaskName() + " of type: " + task.gettaskType().getName() + "\n");
        temp+=("You now have " + num + " tasks in total" + "\n");
        temp+=("Make sure you don't overwhelm yourself!!!");
        t.setResponse(temp);
    }
}

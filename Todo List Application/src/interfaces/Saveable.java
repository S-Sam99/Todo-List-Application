package interfaces;

import exceptions.NoFilesFound;
import model.TodoList;

import java.io.IOException;

public interface Saveable {
    public void saveTasks(String s, TodoList todo) throws NoFilesFound, IOException;
}

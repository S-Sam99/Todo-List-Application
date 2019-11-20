package tests;

import exceptions.NoFilesFound;
import model.TodoList;
import org.junit.jupiter.api.Test;
import ui.UI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.fail;

public class testException {

/*    @BeforeEach
    public void starter() throws IOException{
        UI todo = new UI();
    }
    */
/*    @Test
    public void testtoomanytasksExeption() throws IOException {
        UI todo = new UI();
        Task t1 = new NormalTask("test1", "high");
        Task t2 = new NormalTask("test2", "med");
        Task t3 = new NormalTask("test3", "low");
        Task t4 = new SecretTask("test4", "low");
        Task t5 = new UrgentTask("test5", "high");
        Task t6 = new UrgentTask("test6", "high");
        List<Task> testList = new ArrayList<>();
        testList.add(t1);
        testList.add(t2);
        testList.add(t3);
        testList.add(t4);
        testList.add(t5);
        testList.add(t6);
        try{
            todo.addTask();
            fail("Didn't catch exception");
        } catch (TooManyTasks e){
            System.out.println("There were too many tasks");
        }
    }

    @Test
    public void testgoodamountoftasks() throws IOException {
        UI todo = new UI();
        Task t1 = new NormalTask("test1", "high");
        Task t2 = new NormalTask("test2", "med");
        Task t3 = new NormalTask("test3", "low");
        Task t4 = new SecretTask("test4", "low");
        Task t5 = new UrgentTask("test5", "high");
        Task t6 = new UrgentTask("test6", "high");
        List<Task> testList = new ArrayList<>();
        testList.add(t1);
        testList.add(t2);
        testList.add(t3);
        testList.add(t4);
        testList.add(t5);
        try{
            todo.addTask();
            System.out.println("Operated properly");
        } catch (TooManyTasks e){
            fail("Caught exception in the wrong situation");
        }
    }*/

    @Test
    public void testsnofilesFoundinsave() throws IOException {
        UI ui = new UI();
        TodoList todo = new TodoList();
        try{
            ui.saveTasks("test",todo);
            fail("Did not work properly");
        } catch(FileNotFoundException e){
            System.out.println("Caught exception");
        }
    }

    @Test
    public void testpropersave() throws IOException {
        UI ui = new UI();
        TodoList todo = new TodoList();
        try{
            ui.saveTasks("savedTasks",todo);
            System.out.println("Worked properly");
        } catch(FileNotFoundException e){
            fail("Didn't work as intended");
        }
    }

    @Test
    public void testsnofilesFoundinload() throws IOException {
        UI todo = new UI();
        try{
            todo.load("test");
            fail("Did not work properly");
        } catch(AccessDeniedException e){ //ask about this exception in lab
            System.out.println("Caught exception");
        }
    }

    @Test
    public void testproperload() throws IOException {
        UI todo = new UI();
        try{
            todo.load("savedTasks");
            System.out.println("Worked properly");
        } catch(NoFilesFound e){
            fail("Didn't work as intended");
        }
    }
}

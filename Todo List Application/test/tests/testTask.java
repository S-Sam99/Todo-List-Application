package tests;

import model.SecretTask;
import model.Task;
import model.NormalTask;
import model.UrgentTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testTask {

    @Test
    void setnewnormaltask(){
        Task t = new NormalTask("Make a test", "low");
        assertEquals(t.gettaskName(), "Make a test");
        assertEquals(t  .gettaskPriority(), "low");
        assertEquals(t.gettaskType().getName(), "Normal");
    }

    @Test
    void setnewsecrettask(){
        Task t = new SecretTask("Make a test", "low");
        assertEquals(t.gettaskName(), "Make a test...shhhh");
        assertEquals(t.gettaskPriority(), "low");
        assertEquals(t.gettaskType().getName(), "Secret");
    }

    @Test
    void setnewurgenttask(){
        Task t = new UrgentTask("Make a test", "low");
        assertEquals(t.gettaskName(), "MAKE A TEST");
        assertEquals(t.gettaskPriority(), "ASAP");
        assertEquals(t.gettaskType().getName(), "URGENT");
    }

    @Test
    void testsetnormaltaskname(){
        Task t = new NormalTask("Make a test", "low");
        t.settaskName("I'm changed");
        assertEquals(t.gettaskName(), "I'm changed");
    }

    @Test
    void testsetsecrettaskname(){
        Task t = new SecretTask("Make a test", "low");
        t.settaskName("I'm changed");
        assertEquals(t.gettaskName(), "I'm changed...shhhh");
    }

    @Test
    void testseturgenttaskname(){
        Task t = new UrgentTask("Make a test", "low");
        t.settaskName("I'm changed");
        assertEquals(t.gettaskName(), "I'M CHANGED");
    }

    @Test
    void testsetnormaltaskpriority(){
        Task t = new NormalTask("Make a test", "low");
        t.settaskPriority("high");
        assertEquals(t.gettaskPriority(), "high");
    }

    @Test
    void testsetsecrettaskpriority(){
        Task t = new SecretTask("Make a test", "low");
        t.settaskPriority("high");
        assertEquals(t.gettaskPriority(), "high");
    }

    @Test
    void testseturgenttaskpriority(){
        Task t = new UrgentTask("Make a test", "low");
        t.settaskPriority("high");
        assertEquals(t.gettaskPriority(), "ASAP");
    }
}

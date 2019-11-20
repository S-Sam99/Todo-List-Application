package tests;

import model.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testType {

    @Test
    void newtype(){
        Type t = new Type("This is a test");
        assertEquals(t.getName(), "This is a test");
    }
}

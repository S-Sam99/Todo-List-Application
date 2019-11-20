package tests;

import interfaces.Loadable;
import org.junit.jupiter.api.Test;
import ui.UI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testLoadable {
    Loadable loadme;
    UI todo;

    @Test
    void testload()throws IOException{
        ArrayList<String> listnames = new ArrayList<>();
        ArrayList<String> listpriorities = new ArrayList<>();
        String s1 = "testing";
        String s2 = "test";
        String s3 = "lol";
        String s4 = "loll";
        listnames.add(s1);
        listnames.add(s2);
        listpriorities.add(s3);
        listpriorities.add(s4);
        List<String> tasks = Files.readAllLines(Paths.get("testfile"));
        for (int i=0; i<listnames.size(); i++){
            assertEquals(listnames.get(i), tasks.get(i));
        }
    }
}

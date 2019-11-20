package tests;

import model.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testSaveable {
    @Test
    void testsavetaskNames() throws IOException {
        ArrayList<String> listnames = new ArrayList<>();
        String s1 = "testing";
        String s2 = "test";
        listnames.add(s1);
        listnames.add(s2);
        PrintWriter writern = new PrintWriter("testfile", "UTF-8");
        for (String s : listnames) {
            writern.println(s);
        }
        writern.close();
        List<String> names = Files.readAllLines(Paths.get("testfile"));
        for (int i = 0; i < listnames.size(); i++) {
            assertEquals(listnames.get(i), names.get(i));
        }
    }
}
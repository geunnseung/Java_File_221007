package week3.day4.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadLineContext<T> {

    Dosomething<T> dosomething;

    public List<T> readByLine(String filename) throws IOException {

        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(filename)
        );
        String str;
        while ((str = reader.readLine()) != null) {
            System.out.println(str);
//            PopulationMove pm = parse(str);

        }
        reader.close();
        return result;

    }
}

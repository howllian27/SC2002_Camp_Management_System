package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public List<String> readFile(String filepath) {
        List<String> lines = new ArrayList<>();

        try (FileReader fr = new FileReader(filepath);
             BufferedReader br = new BufferedReader(fr)) {
            
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return lines;
    }
}



package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper responsible for reading txt file.
 * @author Lee Ern Qi Eunice
 * @version 1.0
 * @since 2023-10-29
 */

public class FileHelper {

    /**
     * Reads student/staff txt file returns list of string [name email faculty]
     * @param filepath The filepath of student/staff txt file
     * @return List<String>, list of string [name email faculty]
     */

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



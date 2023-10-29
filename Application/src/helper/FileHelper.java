package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper responsible for reading & writing txt file.
 * @author Lee Ern Qi Eunice
 * @version 2.0
 * @since 2023-10-29
 */

public class FileHelper {

    /**
     * Reads student/staff txt file
     * @param filepath The filepath of student/staff txt file
     * @return List<String>, list of string [name email faculty]
     */

    public List<String> readFile(String filepath) {
        List<String> lines = new ArrayList<>();

        try (FileReader fr = new FileReader(filepath);
             BufferedReader br = new BufferedReader(fr)) {
            
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Writes txt file 
     * @param ReportList The List of information to be generated in report
     */

    public void writeFile(List<String> ReportList){
        try(FileWriter fw = new FileWriter("Report.txt");
            BufferedWriter bw = new BufferedWriter(fw)){

            for (int i = 0; i < ReportList.size(); i++){
                bw.write(ReportList.get(i));
                bw.newLine();
            }

        }catch (IOException e){
            System.err.println("An error occurred while writing the file: " + e.getMessage());
        }

    }
}
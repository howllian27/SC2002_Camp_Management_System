package helper;

import java.util.List;
import helper.FileHelper;

public class mainApp {
    public static void main(String[] args) {
        String filePath = "staff_list.txt"; 

        FileHelper fileHelper = new FileHelper();
        List<String> lines = fileHelper.readFile(filePath);

        System.out.println("Contents of the file:");
        for (String line : lines) {
            System.out.println(line);
        }
        
    }
}

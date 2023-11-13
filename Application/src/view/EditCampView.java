package view;

import java.util.Arrays;
import java.util.Scanner;

import model.Camp;

public class EditCampView {
    
    public Camp editCampInfoView(Camp camp){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                 Camp Information Edit Portal                |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
        System.out.println("Current Camp Details");
        System.out.println("Camp Name: " + camp.getName());
        System.out.println("Dates: " + Arrays.toString(camp.getDates()));
        System.out.println("Registration closing date: " + camp.getClosingDate());
        System.out.println("Location: " + camp.getLocation());
        System.out.println("Total Slots: " + camp.getTotalSlots());
        System.out.println("Committee Slots: " + camp.getCommitteeSlots());
        System.out.println("Description: " + camp.getDescription());
        System.out.println("Visibilty: " + camp.getVisibility());
        System.out.println("");
        while(true){
            System.out.println("What would you like to change?");
            System.out.println("1. Edit Camp Name ");
            System.out.println("2. Edit Dates ");
            System.out.println("3. Edit Registration closing date " );
            System.out.println("4. Edit Location " );
            System.out.println("5. Edit Total Slots " );
            System.out.println("6. Edit No. of Committee Slots " );
            System.out.println("7. Edit Description " );
            System.out.println("8. Edit Visibilty (true/false) " );
            System.out.println("9. Exit Edit Portal  " );
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch(choice){
                case 1: 
                    System.out.print("Enter New Camp Name: ");
                    String newCampName = scanner.nextLine();
                    camp.setName(newCampName);
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    break;
                default:
                    break;
            }
            
            return camp;
        }

        
    }

}

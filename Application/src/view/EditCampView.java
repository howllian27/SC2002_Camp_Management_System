package view;

import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import helper.InputHelper;
import model.Camp;

/**
 * The {@code EditCampView} class provides a user interface for editing camp information.
 * Users can interactively modify various details of a given camp, including its name,
 * dates, registration closing date, location, slots, description, and visibility.
 * The changes made by the user are reflected in the provided {@code Camp} object.
 *
 * <p><b>Note:</b> To exit the edit portal, the user can enter '9' or '0' when prompted for a choice.
 *
 * @author Cheng Lin
 * @version 1.0
 */
public class EditCampView {

    /**
     * Displays the current details of the camp and allows the user to edit specific attributes.
     * The user can choose which camp details to modify and provide new values.
     *
     * @param camp The {@code Camp} object representing the camp to be edited.
     * @return The edited {@code Camp} object reflecting the changes made by the user.
     */
    public Camp editCampInfoView(Camp camp){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
        System.out.println("Visibility: " + camp.getVisibility());
        System.out.println();
        while(true){
            System.out.println("What would you like to change?");
            System.out.println("1. Edit Camp Name ");
            System.out.println("2. Edit Dates ");
            System.out.println("3. Edit Registration closing date " );
            System.out.println("4. Edit Location " );
            System.out.println("5. Edit Total Slots " );
            System.out.println("6. Edit No. of Committee Slots " );
            System.out.println("7. Edit Description " );
            System.out.println("8. Edit Visibility (true/false) " );
            System.out.println("9. Exit Edit Portal  " );
            System.out.print("Enter choice: ");
            int choice = InputHelper.nextInt();

            switch(choice){
                case 1:
                    System.out.print("Enter new camp name: ");
                    String newCampName = InputHelper.nextLine();
                    camp.setName(newCampName);
                    break;
                case 2:
                    System.out.print("Which Date do you want to edit");
                    System.out.print("1. Start Date ");
                    System.out.print("2. End Date ");
                    int datechoice = InputHelper.nextInt();
                    switch(datechoice){
                        case 1:
                            System.out.print("Enter new start date (dd-mm-yyyy) ");
                            Date newStartDate = InputHelper.nextDate();

                            Date[] dates_Start = {newStartDate, camp.getDates()[1]};
                            camp.setDates(dates_Start);
                            break;
                        case 2:
                            System.out.print("Enter new end date (dd-mm-yyyy) ");
                            Date newEndDate = InputHelper.nextDate();

                            Date[] dates_End = {camp.getDates()[1], newEndDate};
                            camp.setDates(dates_End);
                            break;
                    }
                case 3:
                    System.out.print("Enter new registration closing date (dd-mm-yyyy) ");
                    Date newRegistrationClosingDate = InputHelper.nextDate();
                    camp.setClosingDate(newRegistrationClosingDate);
                    break;
                case 4:
                    System.out.print("Enter new camp location: ");
                    String newCampLocation = InputHelper.nextLine();
                    camp.setLocation(newCampLocation);
                    break;
                case 5:
                    System.out.print("Enter new number of total slots: ");
                    int newTotalSlots = InputHelper.nextInt();
                    camp.setTotalSlots(newTotalSlots);
                    break;
                case 6:
                    System.out.print("Enter new number of committee slots: ");
                    int newCommitteeSlots = InputHelper.nextInt();
                    camp.setCommitteeSlots(newCommitteeSlots);
                    break;
                case 7:
                    System.out.print("Enter new Description: ");
                    String newDescription = InputHelper.nextLine();
                    camp.setDescription(newDescription);
                    break;
                case 8:
                    System.out.println("Change Visibility to?");
                    boolean newVisibility = InputHelper.nextBoolean();
                    camp.setVisibility(newVisibility);
                    break;
                default:
                    break;
            }

            return camp;
        }


    }

}

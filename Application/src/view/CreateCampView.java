package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controller.CampOperationsController;
import helper.InputHelper;
import model.CampInformation;
import model.Faculty;
import model.Staff;
import model.User;

/**
 * The {@code CreateCampView} class is responsible for interacting with users to create a new camp.
 * It gathers information about the camp from the user, constructs a {@code CampInformation} object,
 * and invokes the necessary controller methods to create the camp.
 *
 * @author Cheng Lin
 * @version 1.0
 */
public class CreateCampView {
    CampOperationsController campOperationsController = new CampOperationsController();

    /**
     * Gathers information from the user to create a new camp.
     *
     * @param user The user initiating the camp creation (assumed to be a staff member).
     * @return The {@code CampInformation} object representing the newly created camp.
     */
    public CampInformation creatingCamp(User user){
        System.out.println("\n+------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|                    CAMP CREATION PORTAL                     |");
        System.out.println("|                                                             |");
        System.out.println("|                                                             |");
        System.out.println("|        To return to the main menu, simply enter '0'.        |");
        System.out.println("|                                                             |");
        System.out.println("+------------------------------------------------------------+\n");
        Staff staff = (Staff) user;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // 1. Camp Name
        System.out.print("Enter Camp Name: ");
        String campName = InputHelper.nextLine();

        // 2. Date Range
        System.out.print("Enter start date (dd-mm-yyyy): ");
        Date startDate = InputHelper.nextDate();
        System.out.print("Enter end date (dd-mm-yyyy): ");
        Date endDate = InputHelper.nextDate();
        System.out.print("Enter Registration Closing Date (dd-mm-yyyy): ");
        Date registrationClosingDate = InputHelper.nextDate();

        Date[] dates = {startDate, endDate};

        // 4. Faculty
        System.out.print("Enter Faculty: ");
        Faculty faculty = InputHelper.nextFaculty();

        // 5. Location
        System.out.print("Enter Location: ");
        String location = InputHelper.nextLine();

        // 6. Total Slots
        System.out.print("Enter Total Slots: ");
        int totalSlots = InputHelper.nextInt();

        // 7. Committee Slots
        System.out.print("Enter Committee Slots: ");
        int committeeSlots = InputHelper.nextInt();

        // 8. Description
        System.out.print("Enter Description: ");
        String description = InputHelper.nextLine();

        // 9. Visibility
        System.out.print("Is it visible? (true/false): ");
        boolean isVisible = InputHelper.nextBoolean();

        CampInformation campInformation = new CampInformation(campName, dates, registrationClosingDate, faculty, location, totalSlots, committeeSlots, description, staff, isVisible);

        campOperationsController.createCamp(campInformation, staff);
        return campInformation;
    }
}
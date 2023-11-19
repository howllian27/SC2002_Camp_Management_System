package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.CampOperationsController;
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
        Staff staff = (Staff) user;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // 1. Camp Name
        System.out.print("Enter Camp Name: ");
        String campName = scanner.nextLine();

        // 2. Date Range
        System.out.print("Enter start date (dd-mm-yyyy): ");
        String startDateString = scanner.nextLine();
        System.out.print("Enter end date (dd-mm-yyyy): ");
        String endDateString = scanner.nextLine();

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        Date dates[] = {startDate, endDate};

        // 3. Registration Closing Date
        System.out.print("Enter Registration Closing Date (dd-mm-yyyy): ");
        String regClosingDateString = scanner.nextLine();
        Date registrationClosingDate = null;
        try {
            registrationClosingDate = dateFormat.parse(regClosingDateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception as needed
        }

        // 4. Faculty
        System.out.print("Enter Faculty: ");
        String Sfaculty = scanner.nextLine();
        Faculty faculty = Faculty.valueOf(Sfaculty.toUpperCase()); 

        // 5. Location
        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        // 6. Total Slots
        System.out.print("Enter Total Slots: ");
        int totalSlots = scanner.nextInt();

        // 7. Committee Slots
        System.out.print("Enter Committee Slots: ");
        int committeeSlots = scanner.nextInt();

        // 8. Description
        System.out.print("Enter Description: ");
        scanner.nextLine(); // Consume the newline
        String description = scanner.nextLine();

        // 9. Visibility
        System.out.print("Is it visible? (true/false): ");
        boolean isVisible = scanner.nextBoolean();

        CampInformation campInformation = new CampInformation(campName, dates, registrationClosingDate, faculty, location, totalSlots, committeeSlots, description, staff, isVisible);

        campOperationsController.createCamp(campInformation, staff);
        return campInformation;
    }
}
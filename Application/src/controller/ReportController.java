package controller;

import helper.FileHelper;
import helper.InputHelper;
import model.Camp;
import model.CampInformation;
import model.Enquiry;
import model.Staff;
import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.CampDB;
import database.EnquiryDB;

import view.ReportView;
import view.CampListView;

/**
 * The {@code ReportController } class is responsible for generating various reports related to camps and student enquiries.
 * It interacts with the CampDB and EnquiryDB for retrieving necessary data and uses ReportView and CampListView for displaying information.
 *
 * @author Chan Hin Wai Howell
 * @version 1.0
 */
public class ReportController implements BaseController{

    private FileHelper fileHelper;
    CampDB campDB;
    EnquiryDB enquiryDB;

    public ReportController(){
        setMasterVariables();
    }

    @Override
    public void setMasterVariables(){
        this.fileHelper = new FileHelper();
        this.campDB = CampDB.getInstance();
        this.enquiryDB = EnquiryDB.getInstance();
    }

    /**
     * Displays the report menu to the user.
     *
     * @param staff The staff member who is currently logged in.
     */
    public void generateReportsForStaff(Staff staff){
        ReportView.displayReport();
        int choice = InputHelper.nextInt();

        System.out.println("Select the camp you wish to generate a report for:");
        List<Camp> camps = campDB.getAllCamps().values().stream().toList();
        CampListView.displayCampsForStaff(camps);
        System.out.println("Enter the number of the camp you wish to generate a report for:");
        int campChoice = InputHelper.nextInt();
        Camp selectedCamp = camps.get(campChoice - 1);

        switch (choice) {
            case 1 -> {
                System.out.println("Generating Camp Report...");
                generateStudentReport(selectedCamp, "Attendees");
            }
            case 2 -> {
                System.out.println("Generating Camp Performance Report...");
                generateStudentReport(selectedCamp, "Camp committee");
            }
            case 3 -> {
                System.out.println("Generating Student Enquiry Report...");
                generateStudentEnquiryReport(selectedCamp);
            }
        }
    }

    /**
     * Generates a report for a camp.
     *
     * @param selectedCamp The camp to generate a report for.
     * @param filterType The type of report to generate.
     */
    public void generateStudentReport(Camp selectedCamp, String filterType){
        List<String> ReportList = new ArrayList<>();
        CampInformation campDetails = selectedCamp.getCampInformation();
        String cdetails = "Camp Information of " + campDetails.campName + "\n\n" +
                            "Camp Name: " + campDetails.campName + "\n" +
                            "Camp Description: " + campDetails.description + "\n" +
                            "Camp Location: " + campDetails.location + "\n\n" +
                            "Camp Dates: " + Arrays.toString(campDetails.dates) + "\n" +
                            "Camp Registration Closing Date: " + campDetails.registrationClosingDate + "\n\n" +
                            "Camp Faculty: " + campDetails.faculty + "\n\n" +
                            "Camp Total Slots: " + campDetails.totalSlots + "\n" +
                            "Camp Committee Slots: " + campDetails.committeeSlots + "\n\n" +
                            "Camp In Charge: " + campDetails.inCharge.getName() + "\n\n";

        StringBuilder concatenatedUserIDs = new StringBuilder();
        Map<String, Student> CampStudents = switch (filterType) {
            case "Attendees" -> {
                System.out.println("List of Camp Attendees: ");
                yield campDB.getAttendeesForCamp(selectedCamp.getName());
            }
            case "Camp committee" -> {
                System.out.println("List of Camp Committee Members: ");
                yield campDB.getCommitteeMembersForCamp(selectedCamp.getName());
            }
            default -> new HashMap<>();
        };


        for (Student student : CampStudents.values()) {
            if (!concatenatedUserIDs.isEmpty()) {
                concatenatedUserIDs.append(", ");
            }
            concatenatedUserIDs.append(student.getName()).append("(User ID: ").append(student.getID()).append(")");

            if (filterType == "Camp Committee"){
                concatenatedUserIDs.append("Total Points: ").append(student.getPoints()).append("\n");
            }
        }

        ReportList.add(cdetails + concatenatedUserIDs);

        if (filterType.equals("Attendees")){
            fileHelper.writeFile(ReportList, "CampReport_" + campDetails.campName + ".txt");
            System.out.println("Camp Report generated successfully!");
        } else {
            fileHelper.writeFile(ReportList, "CampPerformanceReport_" + campDetails.campName + ".txt");
            System.out.println("Camp Performance Report generated successfully!");
        }
    }

    /**
     * Generates a report for student enquiries in a camp.
     *
     * @param camp The camp to generate the report for.
     */
    public void generateStudentEnquiryReport(Camp camp){
        List<String> reportList = new ArrayList<>();
        List<Enquiry> enquiryList = enquiryDB.getEnquiriesByCamp(camp.getName());
        
         // Adding header to the report
        reportList.add("Student Enquiry Report for " + camp.getCampInformation().campName + "\n");
        reportList.add("List of Enquiries:\n");

        // Formatting each enquiry and adding it to the report
        for (Enquiry enquiry : enquiryList) {
            String enquiryEntry = "Student ID: " + enquiry.getStudent().getName() + "\n" +
                                "Enquiry Text: " + enquiry.getEnquiry() + "\n" +
                                "Response: " + (enquiry.getResponse() != null ? enquiry.getResponse() : "No response yet") + "\n" +
                                "----------------------------------------\n";
            reportList.add(enquiryEntry);
        }

        fileHelper.writeFile(reportList, "StudentEnquiryReport_" + camp.getName() + ".txt");
    }
}
    


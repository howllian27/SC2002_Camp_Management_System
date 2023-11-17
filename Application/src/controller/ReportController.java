package controller;

import helper.FileHelper;
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
import java.util.Scanner;

import database.CampDB;
import database.EnquiryDB;

import view.ReportView;
import view.CampListView;

public class ReportController implements BaseController{

    private FileHelper fileHelper;
    private ReportView reportView;
    private CampListView campListView;
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
        this.reportView = new ReportView();
        this.campListView = new CampListView();
    }

    /**
     * Displays the report menu to the user.
     * @param staff The staff member who is currently logged in.
     */
    public void generateReportsForStaff(Staff staff){
        reportView.displayReport();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        System.out.println("Select the camp you wish to generate a report for:");
        List<Camp> camps = campDB.getAllCamps().values().stream().toList();
        campListView.displayCampsForStaff(camps);
        System.out.println("Enter the number of the camp you wish to generate a report for:");
        int campChoice = sc.nextInt();
        Camp selectedCamp = camps.get(campChoice - 1);

        switch (choice){
            case 1:
                System.out.println("Generating Camp Report...");
                generateStudentReport(selectedCamp, "Attendees");
                break;
            case 2:
                System.out.println("Generating Camp Performance Report...");
                generateStudentReport(selectedCamp, "Camp committee");
                break;
            case 3:
                System.out.println("Generating Student Enquiry Report...");
                generateStudentEnquiryReport(selectedCamp);
                break;
        }
    }

    /**
     * Generates a report for a camp.
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
        Map<String, Student> CampStudents = new HashMap<>();


        switch (filterType) {
            case "Attendees":
                System.out.println("List of Camp Attendees: ");
                CampStudents = campDB.getAttendeesForCamp(selectedCamp.getName());
                break;
            case "Camp committee":
                System.out.println("List of Camp Committee Members: ");
                CampStudents = campDB.getCommitteeMembersForCamp(selectedCamp.getName());
                break;
        }

        for (Student student : CampStudents.values()) {
            if (concatenatedUserIDs.length() > 0) {
                concatenatedUserIDs.append(", ");
            }
            concatenatedUserIDs.append(student.getName() + "(User ID: " + student.getID() + ")");

            if (filterType == "Camp Committee"){
                concatenatedUserIDs.append("Total Points: " + student.getPoints() + "\n");
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
    


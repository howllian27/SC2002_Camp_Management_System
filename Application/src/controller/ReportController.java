package controller;

import helper.FileHelper;
import model.CampInformation;
import model.Staff;
import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import database.CampDB;
import database.UserDB; 

import view.ReportView;

public class ReportController implements BaseController{

    private FileHelper fileHelper;
    private ReportView reportView;
    CampDB campDB;

    public ReportController(){
        setMasterVariables();
    }

    @Override
    public void setMasterVariables(){
        this.fileHelper = new FileHelper();
        this.campDB = CampDB.getInstance();
        this.reportView = new ReportView();
    }

    public void generateReportsForStaff(Staff staff){
        reportView.displayReport();
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                System.out.println("Generating Camp Report...");
                generateStudentReport("ori", "Attendees");
                break;
            case 2:
                System.out.println("Generating Camp Performance Report...");
                break;
            case 3:
                System.out.println("Generating Student Enquiry Report...");
                break;
        }
    }

    public void generateStudentReport(String CampID, String filterType){
        List<String> ReportList = new ArrayList<>();
        CampInformation campDetails = campDB.getCampDetails(CampID);
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
                CampStudents = campDB.getAttendeesForCamp(CampID);
                break;
            case "Camp committee":
                System.out.println("List of Camp Committee Members: ");
                CampStudents = campDB.getCommitteeMembersForCamp(CampID);
                break;
        }

        for (Student student : CampStudents.values()) {
            if (concatenatedUserIDs.length() > 0) {
                concatenatedUserIDs.append(", ");
            }
            concatenatedUserIDs.append(student.getName() + "(User ID: " + student.getID() + ")");
        }

        ReportList.add(cdetails + concatenatedUserIDs);
        fileHelper.writeFile(ReportList, "CampReport_" + campDetails.campName + ".txt");
    }  
}
    


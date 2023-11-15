package controller;

import helper.FileHelper;
import model.CampInformation;
import model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import database.CampDB;
import database.UserDB; 

V
public class ReportController implements BaseController{

    private FileHelper fileHelper;
    CampDB campDB;

    public ReportController(){
        setMasterVariables();
    }

    @Override
    public void setMasterVariables(){
        this.fileHelper = new FileHelper();
        this.campDB = CampDB.getInstance();
    }

    public void generateStudentReport(String CampID, String filterType, String filename){
        List<String> ReportList = new ArrayList<>();
        CampInformation campDetails = campDB.getCampDetails(CampID);
        String cdetails = "CampInformation{" +
                "campName='" + campDetails.campName + '\'' +
                ", dates=" + Arrays.toString(campDetails.dates) +
                ", registrationClosingDate=" + campDetails.registrationClosingDate +
                ", faculty=" + campDetails.faculty +
                ", location='" + campDetails.location + '\'' +
                ", totalSlots=" + campDetails.totalSlots +
                ", committeeSlots=" + campDetails.committeeSlots +
                ", description='" + campDetails.description + '\'' +
                ", inCharge=" + campDetails.inCharge +
                '\n';

        StringBuilder concatenatedUserIDs = new StringBuilder();
        Map<String, Student> CampStudents = new HashMap<>();


        switch (filterType) {
            case "Attendees":
                CampStudents = campDB.getAttendeesForCamp(CampID);
                break;
            case "Camp committee":
                CampStudents = campDB.getCommitteeMembersForCamp(CampID);
                break;
            case "All students":
                CampStudents = campDB.getAllStudentsForCamp(CampID);
                break;
        }

        for (Student student : CampStudents.values()) {
            if (concatenatedUserIDs.length() > 0) {
                concatenatedUserIDs.append(", ");
            }
            concatenatedUserIDs.append(student.getID());
        }

        ReportList.add(cdetails + concatenatedUserIDs);
        fileHelper.writeFile(ReportList, filename);
    }  
        
public void generateCommitteePerformanceReport(String campID){}
}
    


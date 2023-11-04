package model;

public class Enquiry {
    private final Student student;
    private final Camp camp;
    private String enquiry;
    private String response;

    Enquiry(Camp camp, Student student, String enquiry) {
        this.camp = camp;
        this.student = student;
        this.enquiry = enquiry;
    }

    //Getters
    public Student getStudent() { return this.student; }
    public Camp getCamp() { return this.camp; }
    public String getEnquiry() { return this.enquiry; }
    public String getResponse() { return this.response; }

    //Setters
    public void setEnquiry(String enquiry) { this.enquiry = enquiry; }
    public void setResponse(String response) { this.response = response; }
}

package model;

/**
 * The {@code Enquiry} class represents an enquiry made by a student regarding a camp, along with a response.
 * This class encapsulates information about the student, camp, enquiry, and response.
 *
 * @author Shun Jie
 * @version 1.0
 */
public class Enquiry {
    private final Student student;
    private final Camp camp;
    private String enquiry;
    private String response;

    /**
     * Constructs an Enquiry object with the specified camp, student, and enquiry.
     *
     * @param camp The camp related to the enquiry.
     * @param student The student making the enquiry.
     * @param enquiry The text of the enquiry made by the student.
     */
    public Enquiry(Camp camp, Student student, String enquiry) {
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

import java.util.Date;

/***
 * The `CampInformation` class represents information about a camp, including details
 * such as its name, dates, registration information, location and more.
 *
 * @author Ruin9999
 * @version 1.0
 */
public class CampInformation {
    public String campName;
    public Date[] dates;
    public Date registrationClosingDate;
    public Faculty faculty; //User group this camp is open to.
    public String location;
    public int totalSlots;
    public int committeeSlots;
    public String description;
    public Staff inCharge;
    public boolean visibility;
    private final int MAXCOMMITTEESLOTS = 10;

    /***
     * Constructs a new `CampInformation` object with the provided camp details.
     * @param campName The name of the camp.
     * @param dates An array for the dates of the camp.
     * @param registrationClosingDate The closing date for camp registration.
     * @param faculty The faculty or user group this camp is open to.
     * @param location The location of the camp.
     * @param totalSlots The total available slots for the camp.
     * @param committeeSlots The maximum number of committee slots for the camp.
     * @param description A description of the camp.
     * @param inCharge The staff member in charge of the camp.
     * @param visibility A boolean indicating if the camp is visible to students.
     */
    CampInformation(String campName, Date[] dates, Date registrationClosingDate, Faculty faculty,
                    String location, int totalSlots, int committeeSlots, String description, Staff inCharge, boolean visibility) {
        this.campName = campName;
        this.dates = dates;
        this.registrationClosingDate = registrationClosingDate;
        this.faculty = faculty;
        this.location = location;
        this.totalSlots = totalSlots;
        this.committeeSlots = Math.min(committeeSlots, MAXCOMMITTEESLOTS);
        this.description = description;
        this.inCharge = inCharge;
        this.visibility = visibility;
    }


}

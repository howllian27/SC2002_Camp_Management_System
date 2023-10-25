import java.util.Date;

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

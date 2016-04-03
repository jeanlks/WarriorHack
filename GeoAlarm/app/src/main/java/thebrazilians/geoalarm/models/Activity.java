package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class Activity {
    private int ID;
    private String name;
    private String description;
    private AlarmDate AlarmDate;
    private String isRepeatable;
    private MarkerActivity markerActivity;

    public Activity(int ID,String name, String description, AlarmDate AlarmDate,MarkerActivity markerActivity, String isRepeatable) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.AlarmDate = AlarmDate;
        this.markerActivity = markerActivity;
        this.isRepeatable = isRepeatable;
    }
    public Activity(String name, String description, AlarmDate AlarmDate, String isRepeatable) {
        this.name = name;
        this.description = description;
        this.AlarmDate = AlarmDate;
       this.markerActivity = markerActivity;
        this.isRepeatable = isRepeatable;
    }

    public Activity(){

    }

    public MarkerActivity getMarkerActivity() {
        return markerActivity;
    }

    public void setMarkerActivity(MarkerActivity markerActivity) {
        this.markerActivity = markerActivity;
    }

    public String getIsRepeatable() {
        return isRepeatable;
    }

    public void setIsRepeatable(String isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AlarmDate getAlarmDate() {
        return AlarmDate;
    }

    public void setAlarmDate(AlarmDate AlarmDate) {
        this.AlarmDate = AlarmDate;
    }
}

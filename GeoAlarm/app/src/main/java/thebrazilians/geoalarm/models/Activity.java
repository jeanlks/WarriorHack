package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class Activity {
    private int ID;
    private String name;
    private String description;
    private alarmDate alarmDate;
    private double latitude;
    private double longitude;
    private String isRepeatable;


    public Activity(int ID,String name, String description, alarmDate alarmDate,double latitude, double longitude, String isRepeatable) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.alarmDate = alarmDate;
        this.longitude = longitude;
        this.isRepeatable = isRepeatable;
    }
    public Activity(String name, String description, alarmDate alarmDate,double latitude, double longitude,String isRepeatable) {
        this.name = name;
        this.description = description;
        this.alarmDate = alarmDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isRepeatable = isRepeatable;
    }

    public Activity(){

    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public alarmDate getDate() {
        return alarmDate;
    }

    public void setDate(alarmDate alarmDate) {
        this.alarmDate = alarmDate;
    }
}

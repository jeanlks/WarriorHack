package thebrazilians.geoalarm.models;

import java.util.ArrayList;

/**
 * Created by Jean on 4/2/16.
 */
public class MarkerActivity {
    private int ID;
    private String title;
    private Double latitude;
    private Double longitude;
    private ArrayList<Activity> activities;

    public MarkerActivity(String title, Double latitude, Double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MarkerActivity(int ID,String title, Double latitude, Double longitude) {
        this.ID = ID;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public MarkerActivity(){

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}

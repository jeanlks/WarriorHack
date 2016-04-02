package thebrazilians.geoalarm.models;

import java.util.Date;

/**
 * Created by Jean on 4/2/16.
 */
public class Activity {
    private int ID;
    private String title;
    private String description;
    private alarmDate alarmDate;

    public Activity(String title, String description, alarmDate alarmDate) {
        this.title = title;
        this.description = description;
        this.alarmDate = alarmDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

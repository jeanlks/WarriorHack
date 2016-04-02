package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class Activity {
    private int ID;
    private String name;
    private String description;
    private alarmDate alarmDate;

<<<<<<< HEAD
=======

>>>>>>> origin/models
    public Activity(int ID,String name, String description, alarmDate alarmDate) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.alarmDate = alarmDate;
    }
    public Activity(String name, String description, alarmDate alarmDate) {
        this.name = name;
        this.description = description;
        this.alarmDate = alarmDate;
    }

    public Activity(){

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

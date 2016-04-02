package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class Activity {
    private int ID;
    private String name;
    private String description;
    private alarmDate alarmDate;

<<<<<<< Updated upstream
    public Activity(int ID,String title, String description, alarmDate alarmDate) {
        this.ID = ID;
        this.title = title;
=======
    public Activity(String title, String description, alarmDate alarmDate) {
        this.name = name;
>>>>>>> Stashed changes
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

    public String getTitle() {
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

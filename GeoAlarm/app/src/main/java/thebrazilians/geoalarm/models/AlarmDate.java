package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class AlarmDate {

    private int month;
    private int day;
    private int year;
    private int hour;
    private int minutes;

    public AlarmDate(int month, int day, int year, int hour, int minutes) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }



}

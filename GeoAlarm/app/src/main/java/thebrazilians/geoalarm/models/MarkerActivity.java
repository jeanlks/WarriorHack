package thebrazilians.geoalarm.models;

/**
 * Created by Jean on 4/2/16.
 */
public class MarkerActivity {
    private String title;
    private Double latitude;
    private Double longitude;

    public MarkerActivity(String title, Double latitude, Double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
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

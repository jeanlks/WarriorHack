package thebrazilians.geoalarm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import thebrazilians.geoalarm.models.DatabaseHandler;
import thebrazilians.geoalarm.models.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DatabaseHandler db = new DatabaseHandler(this);
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        Activity ac =new Activity("test", "just testing", new alarmDate(03, 10, 2016, 14, 00))  ;
        db.addActivity(ac);
        db.addActivity(new Activity("Jean", "just testando", new alarmDate(12, 10, 2016, 10, 00)));
        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Activity> activities = db.getAllActivities();
        Activity ac1= new Activity();
        for (Activity cn : activities) {
             ac1 = cn;
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " description"+cn.getDescription() +"year :"+cn.getDate().getYear() +"month "+cn.getDate().getMonth();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


        db.deleteActivity(ac1);

        for (Activity cn : activities) {
            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " description"+cn.getDescription() +"year :"+cn.getDate().getYear() +"month "+cn.getDate().getMonth();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}

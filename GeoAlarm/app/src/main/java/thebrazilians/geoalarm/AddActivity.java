package thebrazilians.geoalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View;
import android.app.DialogFragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import thebrazilians.geoalarm.controllers.MarkerActivityController;
import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.AlarmDate;
import thebrazilians.geoalarm.models.DatabaseHandler;
import thebrazilians.geoalarm.models.MarkerActivity;

public class AddActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText placeName;
    private EditText activityTitle;
    private EditText description;
    private TextClock time;
    private TextView date;
    private Activity ac;
    private MarkerActivity mac;
    private DatabaseHandler db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSave = (Button) findViewById(R.id.saveButton);
        placeName = (EditText) findViewById(R.id.editText);
        activityTitle = (EditText) findViewById(R.id.editText3);
        description = (EditText) findViewById(R.id.editText2);
        time = (TextClock) findViewById(R.id.textClock);
        date = (TextView) findViewById(R.id.displayDate);
        db = null;

        final Intent intent = getIntent();
        final Bundle params = intent.getExtras();

        if(params!=null) {
            db = new DatabaseHandler(this);
            final double latitude = params.getDouble("latitude");
            final double longitude = params.getDouble("longitude");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mac = new MarkerActivity(placeName.getText().toString(), latitude, longitude);
                    ac = new Activity(activityTitle.getText().toString(),
                            description.getText().toString(),
                            new AlarmDate(Integer.parseInt(date.getText().toString().substring(0, 1)),
                                    Integer.parseInt(date.getText().toString().substring(3, 4)),
                                    Integer.parseInt(date.getText().toString().substring(6, 7)),
                                    Integer.parseInt(time.getText().toString().substring(0, 1)),
                                    Integer.parseInt(time.getText().toString().substring(3, 4))),
                            "true"
                    );

                    if(params.containsKey("marker_activity_id")) {
                        mac.setID(params.getInt("marker_activity_id"));
                        ac.setIdMarker(params.getInt("marker_activity_id"));
                        MarkerActivityController.createActivity(mac, ac);
                        db.addMarker(mac);
                        db.addActivity(ac,mac.getID());
                    } else {
                        MarkerActivityController.createMarkerActivity(mac, ac);
                        db.addMarker(mac);
                        int maximumIDMarker = db.getMaximumIDMarker();
                        db.addActivity(ac,maximumIDMarker);
                    }

                    Intent acIntent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(acIntent);
                }
            });
        }
    }

    public void onButtonClicked(View v){
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "TimePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }



}

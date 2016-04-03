package thebrazilians.geoalarm;

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

import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.AlarmDate;

public class AddActivity extends AppCompatActivity {

    private Button btnSave;
    private EditText placeName;
    private EditText activityTitle;
    private EditText description;
    private TextClock time;
    private TextView date;
    Activity ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        /*btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac = new Activity(placeName.getText(), description.getText(),new AlarmDate(Integer.parseInt(date.getText().toString())),));
            }
        });*/

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

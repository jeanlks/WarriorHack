package thebrazilians.geoalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.AlarmDate;
import thebrazilians.geoalarm.models.MarkerActivity;

public class EditActivity extends AppCompatActivity {
    Activity activity = new Activity();
    private EditText editTextTitleEdit;
    private EditText editTextDescriptionEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        Bundle params = intent.getExtras();

        if(params!= null){
            activity.setName(params.getString("name"));
            activity.setDescription(params.getString("description"));
            activity.setAlarmDate(new AlarmDate(params.getInt("day"),
                    params.getInt("month"),
                    params.getInt("year"),
                    params.getInt("hour"),
                    params.getInt("minute")));
            activity.setMarkerActivity(new MarkerActivity(params.getString("titlePlace"),
                    params.getDouble("latitude"),
                    params.getDouble("longitude")));
            activity.setIsRepeatable("sRepeatable");

        }

        editTextTitleEdit = (EditText) findViewById(R.id.editTextTitleEdit);
        editTextTitleEdit.setText(activity.getName());

        editTextDescriptionEdit = (EditText) findViewById(R.id.editTextDescriptionEdit);
        editTextDescriptionEdit.setText(activity.getDescription());

    }

}

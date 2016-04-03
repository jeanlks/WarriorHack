package thebrazilians.geoalarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import thebrazilians.geoalarm.adapters.AdapterListView;
import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.AlarmDate;
import thebrazilians.geoalarm.models.DatabaseHandler;
import thebrazilians.geoalarm.models.MarkerActivity;

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Activity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Activity ac = new Activity("Jean","Teste",new AlarmDate(10,12,2010,10,00),new MarkerActivity("Indiana Tech",14.0,3.0),"true");
        DatabaseHandler db = new DatabaseHandler(this);
        db.addActivity(ac);


        mList = db.getAllActivities();

        mListView = (ListView) findViewById(R.id.listViewActivity);
        mListView.setAdapter(new AdapterListView(ListActivity.this,mList));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                Bundle params = new Bundle();
                params.putInt("id", mList.get(position).getID());
                params.putString("name", mList.get(position).getName());
                params.putString("description", mList.get(position).getDescription());
                params.putInt("month", mList.get(position).getAlarmDate().getMonth());
                params.putInt("day", mList.get(position).getAlarmDate().getDay());
                params.putInt("year",mList.get(position).getAlarmDate().getYear());
                params.putString("titlePlace",mList.get(position).getMarkerActivity().getTitle());
                params.putDouble("latitude", mList.get(position).getMarkerActivity().getLatitude());
                params.putDouble("longitude",mList.get(position).getMarkerActivity().getLongitude());
                params.putString("isRepeatable",mList.get(position).getIsRepeatable());
                intent.putExtras(params);
                startActivity(intent);
            }
        });
    }

}

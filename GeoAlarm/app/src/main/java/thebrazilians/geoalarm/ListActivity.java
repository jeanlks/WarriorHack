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
import thebrazilians.geoalarm.models.DatabaseHandler;
import thebrazilians.geoalarm.models.MarkerActivity;

public class ListActivity extends AppCompatActivity {
    private List<Activity> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        DatabaseHandler db = new DatabaseHandler(this);

        mList = db.getAllActivitiesByID(params.getInt("marker_id"));

        ListView mListView = (ListView) findViewById(R.id.listViewActivity);
        if(mList != null && mListView != null) {
            mListView.setAdapter(new AdapterListView(ListActivity.this, mList));
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
                    params.putInt("year", mList.get(position).getAlarmDate().getYear());
                    params.putString("isRepeatable", mList.get(position).getIsRepeatable());
                    intent.putExtras(params);
                    startActivity(intent);
                }
            });
        }
    }

}

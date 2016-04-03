package thebrazilians.geoalarm.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean on 4/2/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "activitiesManager";
    //Table activity
    private static final String TABLE_ACTIVITY = "activity";

    private static final String KEY_ID_ACTIVITY = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_MONTH = "month";
    private static final String KEY_DAY = "day";
    private static final String KEY_YEAR = "year";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTES = "minutes";

    private static final String KEY_isRepeatale = "isRepeatable";

    // Table Marker
    private static final String TABLE_MARKER = "marker";
    private static final String KEY_ID_MARKER = "markerID";
    private static final String KEY_TITLE_PLACE = "titlePlace";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MARKER_TABLE = "CREATE TABLE " + TABLE_MARKER + "("
                + KEY_ID_MARKER + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE_PLACE + " TEXT,"
                + KEY_LATITUDE + " TEXT," + KEY_LONGITUDE + " TEXT " + ");";


        String CREATE_ACTIVITY_TABLE = "CREATE TABLE " + TABLE_ACTIVITY + "("
                + KEY_ID_ACTIVITY + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT, " + KEY_MONTH + " INTEGER," + KEY_DAY + " INTEGER," + KEY_YEAR + " INTEGER, " + KEY_HOUR + " INTEGER,"
                + KEY_MINUTES + " INTEGER, " + KEY_isRepeatale + " TEXT, "
                +KEY_ID_MARKER+" INTEGER,"
                +" FOREIGN KEY (" + KEY_ID_MARKER + ") REFERENCES " + TABLE_MARKER + "( " + KEY_ID_MARKER + " ));";
        db.execSQL(CREATE_MARKER_TABLE);
        db.execSQL(CREATE_ACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MARKER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }


    public void addMarker(MarkerActivity marker) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE_PLACE, marker.getTitle());
        values.put(KEY_LATITUDE, marker.getLatitude());
        values.put(KEY_LONGITUDE, marker.getLongitude());
        db.insert(TABLE_MARKER, null, values);
        db.close();
    }

    public int getMaximumIDMarker(){
       int maximumID = 0;
        String selectQuery =  " SELECT MAX ( "+KEY_ID_MARKER+" ) FROM "+TABLE_MARKER;

       SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.rawQuery(selectQuery, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
             maximumID = mCursor.getInt(0);}

        return maximumID;
    }
    public List<MarkerActivity> getAllMarkers() {
        List<MarkerActivity> markerList = new ArrayList<MarkerActivity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MARKER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MarkerActivity markerActivity = new MarkerActivity();
                markerActivity.setID(cursor.getInt(0));
                markerActivity.setTitle(cursor.getString(1));
                markerActivity.setLatitude(cursor.getDouble(2));
                markerActivity.setLongitude(cursor.getDouble(3));
                // Adding contact to list
                markerList.add(markerActivity);
            } while (cursor.moveToNext());
        }

        // return contact list
        return markerList;
    }

    public void addActivity(Activity activity,int markerID) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, activity.getName());
        values.put(KEY_DESCRIPTION, activity.getDescription());
        values.put(KEY_MONTH, activity.getAlarmDate().getMonth());
        values.put(KEY_DAY, activity.getAlarmDate().getDay());
        values.put(KEY_YEAR, activity.getAlarmDate().getYear());
        values.put(KEY_HOUR, activity.getAlarmDate().getHour());
        values.put(KEY_MINUTES, activity.getAlarmDate().getMinutes());
        values.put(KEY_isRepeatale, activity.getIsRepeatable());
        values.put(KEY_ID_MARKER,markerID);
        db.insert(TABLE_ACTIVITY, null, values);
        db.close();
    }


    public MarkerActivity checkForLocation(double latitude, double longitude) {
        MarkerActivity markerActivity = new MarkerActivity();
        String selectQuery = "SELECT  * FROM " + TABLE_MARKER + " WHERE " + KEY_LATITUDE+ " = " + latitude + " AND " + KEY_LONGITUDE + " = " + longitude;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);



    if (cursor.moveToFirst()) {
            markerActivity.setID(cursor.getInt(0));
            markerActivity.setTitle(cursor.getString(1));
            markerActivity.setLatitude(cursor.getDouble(2));
            markerActivity.setLongitude(cursor.getDouble(3));
        return markerActivity;
        }else{
        return null;
    }



    }

    public List<Activity> getAllActivitiesByID(int idMarker) {
        List<Activity> activityList = new ArrayList<Activity>();

        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY + " WHERE ID = " + idMarker;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setID(Integer.parseInt(cursor.getString(0)));
                activity.setName(cursor.getString(1));
                activity.setDescription(cursor.getString(2));
                activity.setAlarmDate(new AlarmDate(Integer.parseInt(cursor.getString(3)),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7))));
                activity.setIsRepeatable(cursor.getString(8));
                activity.setIdMarker(cursor.getInt(9));
                activityList.add(activity);

            } while (cursor.moveToNext());
        }

        return activityList;
    }


//    public void deleteActivity(Activity activity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_ACTIVITY, KEY_ID + " = ?",
//                new String[]{String.valueOf(activity.getID())});
//        db.close();
//    }

    public int updateActivity(Activity activity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, activity.getName());
        values.put(KEY_DESCRIPTION, activity.getDescription());
        values.put(KEY_MONTH, activity.getAlarmDate().getMonth());
        values.put(KEY_DAY, activity.getAlarmDate().getDay());
        values.put(KEY_YEAR, activity.getAlarmDate().getYear());
        values.put(KEY_HOUR, activity.getAlarmDate().getHour());
        values.put(KEY_MINUTES, activity.getAlarmDate().getMinutes());
        values.put(KEY_isRepeatale, activity.getIsRepeatable());

        return db.update(TABLE_ACTIVITY, values, KEY_ID_ACTIVITY+ " = ?", new String[]{String.valueOf(activity.getID())});
    }


}

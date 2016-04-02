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
    private static final String TABLE_ACTIVITY = "activity";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_MONTH ="month";
    private static final String KEY_DAY ="day";
    private static final String KEY_YEAR ="year";
    private static final String KEY_HOUR ="hour";
    private static final String KEY_MINUTES ="minutes";
    private static final String KEY_isRepeatale = "isRepeatable";


    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
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
        String CREATE_ACTIVITY_TABLE = "CREATE TABLE " + TABLE_ACTIVITY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT, " +KEY_DAY+"INTEGER,"+KEY_MONTH+" INTEGER,"+KEY_YEAR+"INTEGER, "+KEY_HOUR+"INTEGER,"
                +KEY_MINUTES+"INTEGER" +KEY_isRepeatale+ "TEXT" +")";
        db.execSQL(CREATE_ACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACTIVITY);
     onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public void addActivity(Activity activity){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, activity.getName());
        values.put(KEY_DESCRIPTION, activity.getDescription());
        values.put(KEY_MONTH, activity.getDate().getMonth());
        values.put(KEY_DAY, activity.getDate().getDay());
        values.put(KEY_YEAR, activity.getDate().getYear());
        values.put(KEY_HOUR,activity.getDate().getHour());
        values.put(KEY_MINUTES,activity.getDate().getMinutes());

        db.insert(TABLE_ACTIVITY, null, values);
        db.close();
    }

    public Activity getActivity(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, new String[] {KEY_ID ,KEY_NAME, KEY_DESCRIPTION, KEY_MONTH,KEY_DAY, KEY_YEAR,
                                                               KEY_HOUR, KEY_MINUTES}, KEY_ID + "=?", new String [] {String.valueOf(id)},
                                                               null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        Activity activity = new Activity(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                                                       new alarmDate(Integer.parseInt(cursor.getString(3)),
                                                       Integer.parseInt(cursor.getString(4)),
                                                       Integer.parseInt(cursor.getString(5)),
                                                       Integer.parseInt(cursor.getString(6)),
                                                       Integer.parseInt(cursor.getString(7))));

        return activity;
    }


    public List<Activity> getAllActivities(){
        List<Activity> activityList = new ArrayList<Activity>();

        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Activity activity = new Activity();
                activity.setID(Integer.parseInt(cursor.getString(0)));
                activity.setName(cursor.getString(1));
                activity.setDescription(cursor.getString(2));
                activity.setDate(new alarmDate(Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),
                        Integer.parseInt(cursor.getString(7))));
                // Adding contact to list
                activityList.add(activity);
            } while (cursor.moveToNext());
        }

        return activityList;
    }
<<<<<<< HEAD
=======

    public int getActivityCount(){
        String countQuery = "SELECT  * FROM " + TABLE_ACTIVITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

<<<<<<< Updated upstream
>>>>>>> origin/models
=======
        return cursor.getCount();
    }

    public void deleteContact(Activity activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITY, KEY_ID + " = ?",
                new String[] { String.valueOf(activity.getID()) });
        db.close();
    }
>>>>>>> Stashed changes
>>>>>>> origin/models
}

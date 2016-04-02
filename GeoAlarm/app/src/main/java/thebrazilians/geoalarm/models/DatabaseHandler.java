package thebrazilians.geoalarm.models;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

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
    private static final String KEY_DATE ="date";
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
                + KEY_DESCRIPTION + " TEXT, " +KEY_MONTH+"TEXT,"+KEY_DATE+" TEXT,"+KEY_YEAR+"TEXT, "+KEY_HOUR+"TEXT,"
                +KEY_MINUTES+"TEXT" +KEY_isRepeatale+ "TEXT" +")";
        db.execSQL(CREATE_ACTIVITY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACTIVITY);
     onCreate(db);
    }



}

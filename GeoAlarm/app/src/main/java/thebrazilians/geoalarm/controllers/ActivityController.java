package thebrazilians.geoalarm.controllers;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.MarkerActivity;

public abstract class ActivityController {

	// CREATE
	public static boolean createActivity(MarkerActivity markerActivity, Activity activity) {

		// AddActivity
		return false;
	}

	// READ
	public static ArrayList<Activity> getActivitiesByMarkerActivityID(int markerActivityID) {

		// ListActivity
		return null;
	}

	public static ArrayList<Activity> getActivityById(int activityID) {

		// ListActivity
		return null;
	}

	// UPDATE
	public static Activity editActivity(Activity activity) {

		// EditActivity
		return null;
	}


	// DELETE
	public static boolean removeActivity(int activityID) {

		// not yet implemented
		return false;
	}

	public static boolean removeAllActivitiesByMarkerActivityID(int markerActivityID) {

		// not yet implemented
		return false;
	}


}

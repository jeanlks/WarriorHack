package thebrazilians.geoalarm.controllers;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import thebrazilians.geoalarm.models.Activity;
import thebrazilians.geoalarm.models.MarkerActivity;

public abstract class MarkerActivityController {

	// CREATE
	public static boolean createActivity(MarkerActivity markerActivity, Activity activity) {

		// AddActivity
		return false;
	}

	public static boolean createMarkerActivity(String title, LatLng latLng, Activity activity) {

		// AddActivity
		return false;
	}

	// READ
	public static ArrayList<MarkerActivity> getAllMarkersActivities() {

		// MapsActivity
		return null;
	}

	public static MarkerActivity getMarkerActivityInLatLng(LatLng latLng) {

		// MapsActivity
		return null;
	}

	public static ArrayList<Activity> getActivitiesInLatLng(LatLng latLng) {

		// ListActivity
		return null;
	}

	// UPDATE
	public static MarkerActivity editMarkerActivity(MarkerActivity markerActivity) {

		// EditActivity
		return null;
	}

	public static Activity editActivity(Activity activity) {

		// EditActivity
		return null;
	}

	// DELETE
	public static boolean removeActivity(MarkerActivity markerActivity, Activity activity) {

		// not yet implemented
		return false;
	}

	public static boolean removeMarkerActivity(MarkerActivity markerActivity) {

		// not yet implemented
		return false;
	}
}

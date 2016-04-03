package thebrazilians.geoalarm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import thebrazilians.geoalarm.models.DatabaseHandler;
import thebrazilians.geoalarm.models.MarkerActivity;

public class MapsActivity extends AppCompatActivity implements
		OnMyLocationButtonClickListener,
		OnMapReadyCallback,
		ActivityCompat.OnRequestPermissionsResultCallback,
		GoogleMap.OnMarkerClickListener,
		GoogleMap.OnMapLongClickListener,
		GoogleMap.OnInfoWindowClickListener, GoogleMap.OnInfoWindowCloseListener {

	private static final String CLASS_NAME = MapsActivity.class.getSimpleName();
	private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

	private boolean mPermissionDenied = false;
	private GoogleMap mMap;
	private Location mCurrentLocation;
	private DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		SupportMapFragment mapFragment =
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);

		db = new DatabaseHandler(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onMapReady(GoogleMap map) {
		mMap = map;

		generateAllMarkers();
		enableClickListeners();
		enableMyLocation();
		setInfoWindowAdapter();
	}

	private void setInfoWindowAdapter() {

		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {

				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View view = inflater.inflate(R.layout.map_marker_window, null);

				TextView textViewTitle = (TextView) view.findViewById(R.id.markerTitle);
				if (!isMarkerOnModel(marker)) {
					Log.i(CLASS_NAME, "Setting Standard Marker Title");
					textViewTitle.setText("Click to Create a New Activity");
				} else {
					Log.i(CLASS_NAME, "Setting Specialized Marker Title");
					textViewTitle.setText(marker.getTitle());
				}

				return view;
			}

			@Override
			public View getInfoContents(Marker marker) {
				return null;
			}
		});
	}

	private void enableClickListeners() {
		mMap.setOnMyLocationButtonClickListener(this);
		mMap.setOnMapLongClickListener(this);
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);
		mMap.setOnInfoWindowCloseListener(this);
	}

	private void enableMyLocation() {

		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				updateCurrentLocation(location);
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {

			}

			@Override
			public void onProviderEnabled(String provider) {

			}

			@Override
			public void onProviderDisabled(String provider) {

			}
		};

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
				!= PackageManager.PERMISSION_GRANTED) {

			PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
					Manifest.permission.ACCESS_FINE_LOCATION, true);
		} else if (mMap != null) {

			mMap.setMyLocationEnabled(true);
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
					0,
					0,
					locationListener);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					0,
					0,
					locationListener);
		}
	}

	private void updateCurrentLocation(Location location) {

		if(mCurrentLocation == null) {
			mCurrentLocation = location;
			centerByLocation();
		}
		mCurrentLocation = location;
	}

	private void centerByLocation() {

		double latitude = mCurrentLocation.getLatitude();
		double longitude = mCurrentLocation.getLongitude();
		LatLng latLng = new LatLng(latitude, longitude);

		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
	}

	@Override
	public boolean onMyLocationButtonClick() {
		Toast.makeText(this, "Moving to Your Current Location", Toast.LENGTH_SHORT).show();

		return false;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults) {
		if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
			return;
		}

		if (PermissionUtils.isPermissionGranted(permissions, grantResults,
				Manifest.permission.ACCESS_FINE_LOCATION)) {

			enableMyLocation();
		} else {

			mPermissionDenied = true;
		}
	}

	@Override
	protected void onResumeFragments() {
		super.onResumeFragments();
		if (mPermissionDenied) {

			showMissingPermissionError();
			mPermissionDenied = false;
		}
	}

	private void showMissingPermissionError() {
		PermissionUtils.PermissionDeniedDialog
				.newInstance(true).show(getSupportFragmentManager(), "Permission Denied");
	}

	@Override
	public void onMapLongClick(LatLng latLng) {

		Log.i(CLASS_NAME, "Click Received. Latitude: " + latLng.latitude
				+ " | Longitude: " + latLng.longitude);

		Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
				.title("Click to Create a New Activity"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		marker.showInfoWindow();
	}

	@Override
	public boolean onMarkerClick(Marker marker) {

		marker.showInfoWindow();
		return false;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {

		MarkerActivity mac = db.checkForLocation(marker.getPosition().latitude,
				marker.getPosition().longitude);
		if(mac == null) {
			Intent intent = new Intent(getApplicationContext(), AddActivity.class);
			Bundle params = new Bundle();
			params.putDouble("latitude", marker.getPosition().latitude);
			params.putDouble("longitude",marker.getPosition().longitude);
			intent.putExtras(params);
			startActivity(intent);

		} else {
			Intent intent = new Intent(getApplicationContext(), ListActivity.class);
			Bundle params = new Bundle();
			params.putInt("marker_id", mac.getID());
			params.putDouble("latitude", mac.getLatitude());
			params.putDouble("longitude", mac.getLongitude());
			intent.putExtras(params);
			startActivity(intent);
		}
	}

	@Override
	public void onInfoWindowClose(Marker marker) {

		if(!isMarkerOnModel(marker)) {
			Toast.makeText(this, "Marker Removed", Toast.LENGTH_SHORT).show();
			marker.remove();
		}
	}

	private boolean isMarkerOnModel(Marker marker) {

		boolean isMarkerOnModel;

		MarkerActivity markerActivity = db.checkForLocation(marker.getPosition().latitude
				, marker.getPosition().longitude);

		isMarkerOnModel = markerActivity != null;

		return isMarkerOnModel;
	}

	private void generateAllMarkers() {
		List<MarkerActivity> markerActivities = db.getAllMarkers();

		if(markerActivities != null) {
			Log.i(CLASS_NAME, "Generating Markers");
			for(MarkerActivity markerActivity: markerActivities) {

				mMap.addMarker(new MarkerOptions()
						.position(new LatLng(markerActivity.getLatitude()
								, markerActivity.getLongitude()))
						.title(markerActivity.getTitle()));
			}
		}
	}
}
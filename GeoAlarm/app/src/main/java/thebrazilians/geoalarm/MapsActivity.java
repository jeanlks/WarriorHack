package thebrazilians.geoalarm;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity implements
		OnMyLocationButtonClickListener,
		OnMapReadyCallback,
		ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

	private static final String CLASS_NAME = MapsActivity.class.getSimpleName();
	private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

	private boolean mPermissionDenied = false;
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		SupportMapFragment mapFragment =
				(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		mMap = map;

		mMap.setOnMyLocationButtonClickListener(this);
		enableMyLocation();
		enableMapClickListener();
		enableMarkerClickListener();
	}

	private void enableMyLocation() {
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
				!= PackageManager.PERMISSION_GRANTED) {

			PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
					Manifest.permission.ACCESS_FINE_LOCATION, true);
		} else if (mMap != null) {

			mMap.setMyLocationEnabled(true);
		}
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

	private void enableMapClickListener() {

		mMap.setOnMapClickListener(this);
	}

	@Override
	public void onMapClick(LatLng latLng) {

		Log.i(CLASS_NAME, "Click Received. Latitude: " + latLng.latitude
				+ " | Longitude: " + latLng.longitude);

		Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title("New Marker"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		marker.showInfoWindow();
		createNewActivity(marker);
	}

	private void enableMarkerClickListener() {

		mMap.setOnMarkerClickListener(this);
	}

	@Override
	public boolean onMarkerClick(Marker marker) {

		return false;
	}

	private void createNewActivity(Marker marker) {


	}
}

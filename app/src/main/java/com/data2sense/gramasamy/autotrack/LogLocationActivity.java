package com.data2sense.gramasamy.autotrack;

import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class LogLocationActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    public static final String ACTIVITY_TAG = LogLocationActivity.class.getSimpleName();
    public static final String filename = "myLogLocationFile";
    FileOutputStream outputStream;
    FileInputStream inputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setup Google Api Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        setContentView(R.layout.activity_log_location);
        setUpMapIfNeeded();
    }

    @Override
    public void onConnected(Bundle  bundle){
        // get last known location once connected
        Log.i(ACTIVITY_TAG, "GOW:Location services connected.");
        // Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        Location lastKnownLocation = new Location("gps");
        lastKnownLocation.setLatitude(47.606769);
        lastKnownLocation.setLongitude(-122.130718);

        if(lastKnownLocation == null){
            Log.i(ACTIVITY_TAG, "GOW:Location object is null.");
        }else{
            Log.i(ACTIVITY_TAG, "GOW:Location object is FULL.");
            Log.d(ACTIVITY_TAG, lastKnownLocation.toString());
        }

        // log last known location to file
        writeLocationToFile(lastKnownLocation);

        // follow the device; keep logging the location to file
        keepLogging(lastKnownLocation);

        // read back the logged content from file
        readLogged();
    }

    // write the location content to a file in internal storage
    // takes in Location object. [file name from parent class]
    private void writeLocationToFile(Location lastKnownLocation) {
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(lastKnownLocation.toString().getBytes());
            Log.i(ACTIVITY_TAG, "GOW:wrote that to a file");
            outputStream.close();
        } catch (Exception e) {
            Log.e(ACTIVITY_TAG, "GOW:Could not write to file. Here is why?");
            e.printStackTrace();
            Log.e(ACTIVITY_TAG, "GOW:End of StackTrace: Could not write to file. Here is why?");

        }
    }

    // continue to log location to file
    private void keepLogging(Location lastKnownLocation) {
        Double latitude = lastKnownLocation.getLatitude();
        Double longitude = lastKnownLocation.getLongitude();

        // temp logging instead of tracking actual movement
        for(int i=0; i < 20; i=i+1){
            lastKnownLocation.setLatitude(latitude + 1);
            lastKnownLocation.setLongitude(longitude + 1);
            latitude = lastKnownLocation.getLatitude();
            longitude = lastKnownLocation.getLongitude();
            Log.i(ACTIVITY_TAG, lastKnownLocation.toString());
            writeLocationToFile(lastKnownLocation);
        }
    }

    private void readLogged() {
        // read data from file and append lines to a line. log it.
        try {
            inputStream = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Log.i(ACTIVITY_TAG, "GOW:Start of file content");
            Log.i(ACTIVITY_TAG, sb.toString());
            Log.i(ACTIVITY_TAG, "GOW:End of file content");
            outputStream.close();
        } catch (Exception e) {
            Log.e(ACTIVITY_TAG, "GOW:Could not write to file. Here is why?");
            e.printStackTrace();
            Log.e(ACTIVITY_TAG, "GOW:End of StackTrace: Could not write to file. Here is why?");

        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (mGoogleApiClient.isConnected()){
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(ACTIVITY_TAG, "GOW:Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(ACTIVITY_TAG, "GOW:Location services suspended. Please reconnect.");
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(47.6097, -122.3331)).title("Marker"));
    }
}

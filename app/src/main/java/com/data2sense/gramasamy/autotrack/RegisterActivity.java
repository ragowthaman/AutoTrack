package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class RegisterActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final String PREFS_NAME = "MyPrefsFile";
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    public static final String ACTIVITY_TAG = RegisterActivity.class.getSimpleName();

    // get the current system time
    long currentTimeMilliseconds = System.currentTimeMillis();


    @Override
    public void onConnected(Bundle connectionHint) {
        // obtain last known location using google play service
        // Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
        //     mGoogleApiClient);

        // fake it, since emulator is not giving it
        mLastLocation = new Location("gps");
        mLastLocation.setLongitude(-122.130718);
        mLastLocation.setLatitude(47.606769);
        if (mLastLocation == null) {
            Log.i(ACTIVITY_TAG, "GOW:Location object is null");
        } else {
            Log.i(ACTIVITY_TAG, "GOW:Location object is FULL");
            Log.d(ACTIVITY_TAG, mLastLocation.toString());
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection has been interrupted.
        // Disable any UI components that depend on Google APIs
        // until onConnected() is called.
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // This callback is important for handling errors that
        // may occur while attempting to connect with Google.
        //
        // More about this in the 'Handle Connection Failures' section.

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        // Get current Registration details the SharedPreferences;
        // display them as defaults in the form
        // helps user not to retype everything
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        String currentLicenceNumber = settings.getString("CURRENT_LICENCE_NUMBER", "----");
        String currentDriverName = settings.getString("CURRENT_DRIVER_NAME", "----");
        String currentDriverPhone = settings.getString("CURRENT_DRIVER_PHONE", "----");
        Long timeRegistered = settings.getLong("CURRENT_REGISTRATION_TIME", 0);
        String longitudeRegistered = settings.getString("CURRENT_REGISTRATION_LONGITUDE", "----");
        String latitudeRegistered = settings.getString("CURRENT_REGISTRATION_LATITUDE", "----");


        // Set the content for this activity
        setContentView(R.layout.activity_register);

        // inject variables from prefFile into Registration Form
        TextView licenceNumberTextView = (TextView) findViewById(R.id.auto_licence_edit_text);
        licenceNumberTextView.setText(currentLicenceNumber);

        TextView driverNameTextView = (TextView) findViewById(R.id.driver_name_edit_text);
        driverNameTextView.setText(currentDriverName);

        TextView driverPhoneTextView = (TextView) findViewById(R.id.driver_phone_edit_text);
        driverPhoneTextView.setText(currentDriverPhone);

        TextView registeredDateTextView = (TextView) findViewById(R.id.registration_date_display_text_view);
        registeredDateTextView.setText(Long.toString(timeRegistered));

        TextView registeredLongitudeTextView = (TextView) findViewById(R.id.register_longitude);
        registeredLongitudeTextView.setText(longitudeRegistered);

        TextView registeredLatitudeTextView = (TextView) findViewById(R.id.register_lattitude);
        registeredLatitudeTextView.setText(latitudeRegistered);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //* when user clicks Register, record the info and take them to Start A new trip screen
    public void registerDriverAndDevice(View view) {

        //takes user to Start a trip activity
        Intent intent = new Intent(this, StartTripActivity.class);

        EditText licenceNumberEditText = (EditText) findViewById(R.id.auto_licence_edit_text);
        String licenceNumber = licenceNumberEditText.getText().toString();

        EditText driverNameEditText = (EditText) findViewById(R.id.driver_name_edit_text);
        String driverName = driverNameEditText.getText().toString();

        EditText driverPhoneEditText = (EditText) findViewById(R.id.driver_phone_edit_text);
        String driverPhone = driverPhoneEditText.getText().toString();

        // Store the collected registration info in SharedPreference. Obtain in activities where needed
        // no more passing variables via intent
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("CURRENT_LICENCE_NUMBER", licenceNumber);
        editor.putString("CURRENT_DRIVER_NAME", driverName);
        editor.putString("CURRENT_DRIVER_PHONE", driverPhone);
        editor.putLong("CURRENT_REGISTRATION_TIME", currentTimeMilliseconds);
        editor.putString("CURRENT_REGISTRATION_LATITUDE", Double.toString(mLastLocation.getLatitude()));
        editor.putString("CURRENT_REGISTRATION_LONGITUDE", Double.toString(mLastLocation.getLongitude()));

        // Apply the edits!
        editor.apply();

        startActivity(intent);
    }


}

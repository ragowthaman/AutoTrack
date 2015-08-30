package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.data2sense.gramasamy.autotrack.MainActivity.*;

public class StartTripActivity extends AppCompatActivity {

    public static final String PREFS_NAME = RegisterActivity.PREFS_NAME;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get current Registration details the SharedPreferences
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        String currentLicenceNumber = settings.getString("CURRENT_LICENCE_NUMBER", "----");
        String currentDriverName = settings.getString("CURRENT_DRIVER_NAME", "----");
        String currentDriverPhone = settings.getString("CURRENT_DRIVER_PHONE", "----");

        // Get the intent from RegisterActivity
        Intent intent = getIntent();

        // Set the content for this activity
        setContentView(R.layout.activity_start_trip);

        // inject variables from prefFile into activity_start_trip layout placeholders
        TextView licenceNumberTextView = (TextView) findViewById(R.id.auto_licence_display);
        licenceNumberTextView.setText(currentLicenceNumber);

        TextView driverNameTextView = (TextView) findViewById(R.id.driver_name_display);
        driverNameTextView.setText(currentDriverName);

        TextView driverPhoneTextView = (TextView) findViewById(R.id.driver_phone_display);
        driverPhoneTextView.setText(currentDriverPhone);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_strat_trip, menu);
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

    /** Called when the user clicks Start logging button */
    public void displayLocationLogScreen(View view){
        Intent intent = new Intent(this, LocationLogActivity.class);
        startActivity(intent);
    }
}

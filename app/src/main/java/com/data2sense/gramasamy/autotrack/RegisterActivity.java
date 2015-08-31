package com.data2sense.gramasamy.autotrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    // get the current system time
    long currentTimeMilliseconds = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get current Registration details the SharedPreferences;
        // display them as defaults in the form
        // helps user not to retype everything
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        String currentLicenceNumber = settings.getString("CURRENT_LICENCE_NUMBER", "----");
        String currentDriverName = settings.getString("CURRENT_DRIVER_NAME", "----");
        String currentDriverPhone = settings.getString("CURRENT_DRIVER_PHONE", "----");
        Long timeRegistered = settings.getLong("CURRENT_REGISTRATION_TIME", 0);


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
    public void registerDriverAndDevice(View view){

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

        // Apply the edits!
        editor.apply();

        startActivity(intent);
    }
}

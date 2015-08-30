package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.data2sense.gramasamy.autotrack.MainActivity.*;

public class StartTripActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setContentView(R.layout.activity_start_trip);

        // inject variables into activity_start_trip layout placeholders
        String licenceNumber = intent.getStringExtra(RegisterActivity.LICENCE_NUMBER);
        TextView licenceNumberTextView = (TextView) findViewById(R.id.auto_licence_display);
        licenceNumberTextView.setText(licenceNumber);

        String driverName = intent.getStringExtra(RegisterActivity.DRIVER_NAME);
        TextView driverNameTextView = (TextView) findViewById(R.id.driver_name_display);
        driverNameTextView.setText(driverName);

        String driverPhone = intent.getStringExtra(RegisterActivity.DRIVER_PHONE);
        TextView driverPhoneTextView = (TextView) findViewById(R.id.driver_phone_display);
        driverPhoneTextView.setText(driverPhone);

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

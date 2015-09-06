package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class MiddleMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location_log, menu);
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

    // NOT very DRY. Fix it
    //// TODO: 8/30/15
    /** Called when the user clicks the Start trip button */
    public void displayStartTripScreen(View view){
        //display start activity screen
        Intent intent = new Intent(this, StartTripActivity.class);
        startActivity(intent);
    }

    /** Called when the user clicks Start logging button */
    public void displayLocationLogScreen(View view){
        Intent intent = new Intent(this, MiddleMenuActivity.class);
        startActivity(intent);
    }


    /** Called when the user clicks Start logging button */
    public void displayRegistrationForm(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    /** opens LogLocation activity when user cliks start logging button */
    public void startLocationLoggingActivity(View view){
        Intent intent = new Intent(this, LogLocationActivity.class);
        startActivity(intent);
    }

}

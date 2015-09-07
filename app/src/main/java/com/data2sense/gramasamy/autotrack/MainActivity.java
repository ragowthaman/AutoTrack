package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /** Called when the user clicks the Register button */
    public void displayRegistrationForm(View view){
        //display registration form
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

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
}

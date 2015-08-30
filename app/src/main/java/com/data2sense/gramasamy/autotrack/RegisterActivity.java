package com.data2sense.gramasamy.autotrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    public final static String LICENCE_NUMBER = "com.data2sense.gramasamy.autotrack.LICENCE_NUMBER";
    public final static String DRIVER_NAME = "com.data2sense.gramasamy.autotrack.DRIVER_NAME";
    public final static String DRIVER_PHONE = "com.data2sense.gramasamy.autotrack.DRIVER_PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        intent.putExtra(LICENCE_NUMBER, licenceNumber);

        EditText driverNameEditText = (EditText) findViewById(R.id.driver_name_edit_text);
        String driverName = driverNameEditText.getText().toString();
        intent.putExtra(DRIVER_NAME, driverName);

        EditText driverPhoneEditText = (EditText) findViewById(R.id.driver_phone_edit_text);
        String driverPhone = driverPhoneEditText.getText().toString();
        intent.putExtra(DRIVER_PHONE, driverPhone);

        startActivity(intent);
    }
}

package com.verra.frc3512scouting;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;


public class Auton extends ActionBarActivity {

    String matchNumber;
    String teamNumber;

    String yellowStacked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auton);

        yellowStacked = "0";

        Intent intent = getIntent();
        matchNumber = intent.getStringExtra("matchNumber");
        teamNumber = intent.getStringExtra("teamNumber");

        ((TextView)findViewById(R.id.auton_header)).setText("Autonomous - " + teamNumber);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auton, menu);
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

    public void onYellowToteRadioClicked(View view)
    {
        RadioButton button = (RadioButton)view;
        boolean checked = button.isChecked();
        if(checked)
        {
            yellowStacked = button.getText().toString();

        }

    }

    public void onBinsMovedRadioClicked(View view)
    {

    }

    public void onBinsMovedFromStepRadioClicked(View view)
    {


    }

    public void onNextClicked(View view)
    {

    }

}
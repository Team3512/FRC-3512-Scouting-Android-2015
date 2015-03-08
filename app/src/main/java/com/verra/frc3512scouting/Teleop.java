package com.verra.frc3512scouting;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Teleop extends ActionBarActivity {

    String matchNumber;
    String teamNumber;

    String yellowStacked;
    String binsMoved;
    String binsMovedFromStep;

    boolean fullyAutonZone;
    boolean toteSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        Intent intent = getIntent();

        matchNumber = intent.getStringExtra("matchNumber");
        teamNumber = intent.getStringExtra("teamNumber");

        yellowStacked = intent.getStringExtra("yellowStacked");
        binsMoved = intent.getStringExtra("binsMoved");
        binsMovedFromStep = intent.getStringExtra("binsMoveFromStep");

        fullyAutonZone = intent.getBooleanExtra("fullyAutonZone", fullyAutonZone);
        toteSet = intent.getBooleanExtra("toteSet", toteSet);

        ((TextView)findViewById(R.id.teleop_header)).setText("Teleop - " + teamNumber);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_teleop, menu);
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

    public void onTotesStepRadioClicked(View view)
    {

    }

    public void onStackedTopRadioClicked(View view)
    {

    }

}

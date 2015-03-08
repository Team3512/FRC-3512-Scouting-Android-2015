package com.verra.frc3512scouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MatchInfo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_info);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match_info, menu);
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

    public void onNextClicked(View view)
    {
        String matchNumber = ((EditText)findViewById(R.id.match_number_edit)).getText().toString();
        String teamNumber = ((EditText)findViewById(R.id.team_number_edit)).getText().toString();
        if(matchNumber.equals("") || teamNumber.equals(""))
        {
            Toast.makeText(this, "Fill out all fields before continuing", Toast.LENGTH_SHORT).show();
            return;

        }

        Intent intent = new Intent(this, Auton.class);
        intent.putExtra("matchNumber", matchNumber);
        intent.putExtra("teamNumber", teamNumber);
        startActivity(intent);

    }
}

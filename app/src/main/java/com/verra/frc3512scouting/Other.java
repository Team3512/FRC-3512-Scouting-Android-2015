package com.verra.frc3512scouting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Other extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        ((EditText)findViewById(R.id.scout_name)).setText(MainActivity.getScouterName(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other, menu);
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

    public void onSaveClicked(View view)
    {
        String scoutName = ((EditText)findViewById(R.id.scout_name)).getText().toString();
        if(scoutName.equals(""))
        {
            Toast.makeText(this, "Please fill out your name", Toast.LENGTH_SHORT).show();
            return;

        }

        MainActivity.setScouterName(this, scoutName);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}

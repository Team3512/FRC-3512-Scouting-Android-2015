package com.verra.frc3512scouting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    ViewPager m_viewPager;
    PagerAdapter m_pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        m_viewPager = (ViewPager) findViewById(R.id.pager);
        m_viewPager.setAdapter(m_pagerAdapter);

        if(getHostLocation(this).equals(""))
        {
            setHostLocation(this, "http://scoutdb.frc3512.com/write");
        }
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
            final Context ctx = this;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Host Location");
            final EditText input = new EditText(this);
            input.setText(getHostLocation(ctx));
            input.setInputType(InputType.TYPE_TEXT_VARIATION_URI);
            builder.setView(input);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    setHostLocation(ctx, input.getText().toString());
                }

            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onNewMatchClicked(View view)
    {

    }

    public void onSubmitLocalDataClicked(View view)
    {

    }

    static public String getHostLocation(Context ctx)
    {
        SharedPreferences prefs = ctx.getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        return prefs.getString("host_location", "");

    }

    static public void setHostLocation(Context ctx, String location)
    {
        SharedPreferences prefs = ctx.getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("host_location", location);
        editor.commit();

    }

    static public String getScouterName(Context ctx)
    {
        SharedPreferences prefs = ctx.getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        return prefs.getString("scouter", "");

    }

    static public void setScouterName(Context ctx, String scouter)
    {
        SharedPreferences prefs = ctx.getSharedPreferences(MainActivity.class.getSimpleName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("scouter", scouter);
        editor.commit();

    }

}

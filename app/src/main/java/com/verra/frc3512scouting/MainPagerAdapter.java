package com.verra.frc3512scouting;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by acf on 3/10/15.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment;

        switch(i) {
            case 0:
                // Match Info
                fragment = new MatchInfoFragment();
                break;
            case 1:
                // Autonomous
                fragment = new AutonomousFragment();
                break;
            case 2:
                // Teleoperated
                fragment = new TeleopFragment();
                break;
            case 3:
                fragment = new OtherFragment();
                // Other
                break;
            default:
                fragment = null;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

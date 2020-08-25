package com.trishasofttech.bulletcricketlive.livetabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class LivePager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    String sKey;
    String format;

    //Constructor to the class
    public LivePager(FragmentManager fm, int tabCount, String skey,String format) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
        this.sKey = skey;
        this.format = format;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Liveline tab1 = new Liveline();
                return tab1;
            case 1:
                MatchInfo tab2 = new MatchInfo();
                Bundle bundle2 = new Bundle (  );
                bundle2.putString ( "skey",sKey );
                bundle2.putString ( "format",format );
                tab2.setArguments ( bundle2 );
                return tab2;
            case 2:
                Scorecard tab3 = new Scorecard();
                Bundle bundle = new Bundle (  );
                bundle.putString ( "skey",sKey );
                bundle.putString ( "format",format );
                tab3.setArguments ( bundle );

                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
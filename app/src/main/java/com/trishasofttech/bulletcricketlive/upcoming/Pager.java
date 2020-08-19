package com.trishasofttech.bulletcricketlive.upcoming;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.trishasofttech.bulletcricketlive.Tabs.AllUpcoming;
import com.trishasofttech.bulletcricketlive.Tabs.OdiUpcoming;
import com.trishasofttech.bulletcricketlive.Tabs.T10Upcoming;
import com.trishasofttech.bulletcricketlive.Tabs.T20Upcoming;
import com.trishasofttech.bulletcricketlive.Tabs.TestUpcoming;


public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                AllUpcoming tab1 = new AllUpcoming();
                return tab1;
            case 1:
                TestUpcoming tab2 = new TestUpcoming();
                return tab2;
            case 2:
                OdiUpcoming tab3 = new OdiUpcoming();
                return tab3;
            case 3:
                T20Upcoming tab4 = new T20Upcoming();
                return tab4;
            case 4:
                T10Upcoming tab5 = new T10Upcoming();
                return tab5;
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
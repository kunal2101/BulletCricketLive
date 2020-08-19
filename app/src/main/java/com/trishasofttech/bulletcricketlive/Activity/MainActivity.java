package com.trishasofttech.bulletcricketlive.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.trishasofttech.bulletcricketlive.Fragment.AboutUsFragment;
import com.trishasofttech.bulletcricketlive.Fragment.AdvertiseFragment;
import com.trishasofttech.bulletcricketlive.Fragment.Home.HomeFragment;
import com.trishasofttech.bulletcricketlive.Fragment.PrivacyFragment;
import com.trishasofttech.bulletcricketlive.R;
import com.trishasofttech.bulletcricketlive.TestingActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState, 0);
    }

    /**
     * Initialize all widgets
     */
    private void initializeViews() {

        /*bottom navigation area*/
        toolbar = findViewById(R.id.toolbar_id);
        toolbar.setTitle(R.string.toolbar_title);
        setSupportActionBar(toolbar);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_news, R.id.navigation_live, R.id.navigation_result, R.id.navigation_upcoming)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



        drawerLayout = findViewById(R.id.drawer_layout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);
        ImageView iv_fb, iv_insta, iv_youtube;
        iv_fb = navigationView.findViewById(R.id.nv_fb);
        iv_insta = navigationView.findViewById(R.id.nv_insta);
        iv_youtube = navigationView.findViewById(R.id.nv_youtube);
        iv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fb= new Intent(Intent.ACTION_VIEW);
                fb.setData(Uri.parse(""));
                startActivity(fb);
            }
        });

        iv_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insta= new Intent(Intent.ACTION_VIEW);
                insta.setData(Uri.parse("https://www.instagram.com/bulletcricketlive/"));
                startActivity(insta);
            }
        });

        iv_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent youtube= new Intent(Intent.ACTION_VIEW);
                youtube.setData(Uri.parse("https://www.youtube.com/channel/UC5NN3Ym0I2YbOy1b9L0Y9pA"));
                startActivity(youtube);
            }
        });

        //darkModeSwitch = (SwitchCompat)navigationView.getMenu().findItem(R.id.nav_darkmode_id).getActionView();
    }

    /**
     * Checks if the savedInstanceState is null - onCreate() is ran
     * If so, display fragment of navigation drawer menu at position itemIndex and
     * set checked status as true
     *
     * @param savedInstanceState
     * @param itemIndex
     */
    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex) {
        if (savedInstanceState == null) {
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    /**
     * Creates an instance of the ActionBarDrawerToggle class:
     * 1) Handles opening and closing the navigation drawer
     * 2) Creates a hamburger icon in the toolbar
     * 3) Attaches listener to open/close drawer on icon clicked and rotates the icon
     */
    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_home_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new HomeFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_about_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new AboutUsFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.nav_advertise_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new AdvertiseFragment())
                        .commit();
                closeDrawer();
                break;

            case R.id.nav_privacy_id:
                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new PrivacyFragment())
                        .commit();
                closeDrawer();
                break;


            case R.id.nav_rateus_id:
                /*rate application on google playstore*/
                Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
                }
                closeDrawer();
                break;

        }
        return true;
    }

    /**
     * Checks if the navigation drawer is open - if so, close it
     */
    private void closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}

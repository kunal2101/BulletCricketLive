package com.trishasofttech.bulletcricketlive.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.trishasofttech.bulletcricketlive.R;

public class UpcomingFragment extends Fragment {

    private UpcomingModel homeViewModel;
    private RecyclerView recyclerView;
    private Button btn_all, btn_odi, btn_t20;
    private String upcoming_url;
    UpcomingAdapter adapter;
    int btnid;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(UpcomingModel.class);
        View v = inflater.inflate(R.layout.fragment_upcoming, container, false);

        //Initializing the tablayout
        tabLayout = v.findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
        tabLayout.addTab(tabLayout.newTab().setText("TEST"));
        tabLayout.addTab(tabLayout.newTab().setText("ODI"));
        tabLayout.addTab(tabLayout.newTab().setText("T20"));
        tabLayout.addTab(tabLayout.newTab().setText("T10"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Initializing viewPager
        viewPager = v.findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getParentFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        /*btn_all = v.findViewById(R.id.btn_all_cat);
        btn_odi = v.findViewById(R.id.btn_odi);
        btn_t20 = v.findViewById(R.id.btn_t20);

        // Inflate the layout for this fragment
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_upcoming);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //loadupcoming("http://searchkero.com/cricket/upcoming.json");

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnid =1;
                upcoming_url= "http://searchkero.com/cricket/upcoming.json";
                adapter  = new UpcomingAdapter(getActivity(), UpcomingHelper.name, UpcomingHelper.date,
                        UpcomingHelper.id, btnid);
                adapter.clear();
                loadupcoming(upcoming_url);

                recyclerView.setAdapter(adapter);
                //progressBar.setVisibility(View.GONE);
            }
        });

        btn_t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnid=2;
                upcoming_url= "http://searchkero.com/cricket/upcoming_t20.json";
                adapter  = new UpcomingAdapter(getActivity(), UpcomingHelper.name, UpcomingHelper.date,
                        UpcomingHelper.id, btnid);
                adapter.clear();
                loadupcoming(upcoming_url);

                recyclerView.setAdapter(adapter);
            }
        });

        btn_odi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnid =3;
                upcoming_url= "http://searchkero.com/cricket/upcoming_odi.json";
                adapter  = new UpcomingAdapter(getActivity(), UpcomingHelper.name, UpcomingHelper.date,
                        UpcomingHelper.id, btnid);
                adapter.clear();
                loadupcoming(upcoming_url);

                recyclerView.setAdapter(adapter);
            }
        });*/

        return v;
    }

    /*private void loadupcoming(String url) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                adapter.clear();
                UpcomingHelper parse = new UpcomingHelper(response);
                parse.upcominghelper();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                //progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }*/
}

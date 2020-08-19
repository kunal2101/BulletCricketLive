package com.trishasofttech.bulletcricketlive.Fragment.Home;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.trishasofttech.bulletcricketlive.AllNewsApdater;
import com.trishasofttech.bulletcricketlive.AllNewsHelper;
import com.trishasofttech.bulletcricketlive.R;
import com.trishasofttech.bulletcricketlive.upcoming.UpcomingHelper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    /** Constant value for the news loader ID. */
    private static final int NEWS_LOADER_ID = 1;
    /** Adapter for the list of news */

    /** TextView that is displayed when the recycler view is empty */
 //   private TextView mEmptyStateTextView;
    /** Loading indicator that is displayed before the first load is completed */
    private View mLoadingIndicator;
    /** The {@link SwipeRefreshLayout} that detects swipe gestures and
     * triggers callbacks in the app.
     */
    //private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView_series, recyclerView;
    ImageView iv_first;
    TextView tv_first;
    private String upcoming_url = "http://cricapi.com/api/matchCalendar?apikey=QU2rB4akb2SbwAmUFTwtZQ8whBX2";
    private String top_series_url = "http://searchkero.com/cricket/topseries.json";
    private String allnews_url = "http://searchkero.com/cricket/latestnews.json";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        tv_first = rootView.findViewById(R.id.tv_first);
        iv_first = rootView.findViewById(R.id.iv_first);

        recyclerView_series = rootView.findViewById(R.id.recyclerview_series);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView_series.setHasFixedSize(true);
        recyclerView_series.setLayoutManager(linearLayoutManager);
        loadupcoming();
        // Find a reference to the {@link RecyclerView} in the layout
        // Replaced RecyclerView with EmptyRecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);

        // Set the layoutManager on the {@link RecyclerView}
        recyclerView.setLayoutManager(layoutManager);

        // Find the SwipeRefreshLayout
      //  mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        // Set the color scheme of the SwipeRefreshLayout
        /*mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.swipe_color_1),
                getResources().getColor(R.color.swipe_color_2),
                getResources().getColor(R.color.swipe_color_3),
                getResources().getColor(R.color.swipe_color_4));*/

        initiateRefresh();
        // Set up OnRefreshListener that is invoked when the user performs a swipe-to-refresh gesture.
        /*mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                // restart the loader
                initiateRefresh();
                Toast.makeText(getActivity(), getString(R.string.updated_just_now),
                        Toast.LENGTH_SHORT).show();
            }
        });*/


        // Find the empty view from the layout and set it on the new recycler view
        /*mEmptyStateTextView = rootView.findViewById(R.id.empty_view);
        mRecyclerView.setEmptyView(mEmptyStateTextView);*/



        // Check for network connectivity and initialize the loader
        //initializeLoader(isConnected());

        return rootView;
    }

    private void initiateRefresh() {
        StringRequest request = new StringRequest(allnews_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jo = new JSONObject(response);
                    JSONArray ja = jo.getJSONArray("data");
                    JSONObject jo1 = ja.getJSONObject(0);
                    tv_first.setText(jo1.getString("title"));
                    Glide.with(getActivity()).load(jo1.getString("url")).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_first);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                AllNewsHelper parse = new AllNewsHelper(response);
                parse.upcominghelper();

                AllNewsApdater adapter = new AllNewsApdater(getActivity(), AllNewsHelper.title, AllNewsHelper.desc,
                        AllNewsHelper.date, AllNewsHelper.url, AllNewsHelper.id);
                recyclerView.setAdapter(adapter);
                //mSwipeRefreshLayout.setRefreshing(false);
                //progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                //progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void loadupcoming() {
        StringRequest request = new StringRequest(top_series_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                UpcomingHelper parse = new UpcomingHelper(response);
                parse.upcominghelper();

                SeriesAdapter adapter = new SeriesAdapter(getActivity(), UpcomingHelper.name, UpcomingHelper.date,
                        UpcomingHelper.id );
                recyclerView_series.setAdapter(adapter);
                //progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                //progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }



    /**
     *  Check for network connectivity.
     */
    private boolean isConnected() {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return (networkInfo != null && networkInfo.isConnected());
    }


}

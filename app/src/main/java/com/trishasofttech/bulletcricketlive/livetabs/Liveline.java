package com.trishasofttech.bulletcricketlive.livetabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trishasofttech.bulletcricketlive.R;
import com.trishasofttech.bulletcricketlive.UpcomingHelper;
import com.trishasofttech.bulletcricketlive.upcoming.UpcomingAdapter;

public class Liveline extends Fragment {
    private RecyclerView recyclerView;
    private String allupcoming = "http://searchkero.com/cricket/upcoming.json";
    UpcomingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.liveline_layout, container, false);


        //loadmatch();
        return v;
    }

    private void loadmatch() {
        StringRequest request = new StringRequest(allupcoming, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


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
    }
}


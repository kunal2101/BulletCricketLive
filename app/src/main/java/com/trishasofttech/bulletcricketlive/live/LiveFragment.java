package com.trishasofttech.bulletcricketlive.live;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trishasofttech.bulletcricketlive.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LiveFragment extends Fragment {

    private LiveModel notificationsViewModel;
    private RecyclerView recyclerView;

    private String live_url= "http://cricapi.com/api/cricket/?apikey=QU2rB4akb2SbwAmUFTwtZQ8whBX2";
    String url = "http://cricapi.com/api/cricketScore?apikey=QU2rB4akb2SbwAmUFTwtZQ8whBX2&unique_id=";
    String live_match = "https://rest.cricketapi.com/rest/v2/recent_matches/?access_token=2s1294346407548948481s1298512701944908478&card_type=summary_card";
     Handler handler;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(LiveModel.class);
        View v = inflater.inflate(R.layout.base_layout, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_upcoming);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadupcoming();
        handler = new Handler ();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 20 seconds
                loadupcoming();
                Toast.makeText ( getActivity (),"update",Toast.LENGTH_LONG ).show ();
            }
        }, 10000);

        return v;
    }

    private void loadupcoming() {

        StringRequest request = new StringRequest(live_match, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


               if(LiveHelper.arrLiveData.size () >0){
                   LiveHelper.arrLiveData.clear ();
               }

                LiveHelper parse = new LiveHelper(response);

                parse.livehelper();



                LiveAdapter adapter = new LiveAdapter(getActivity(), LiveHelper.match, LiveHelper.id,LiveHelper.matchseries,LiveHelper.arrLiveData);

                recyclerView.setAdapter(adapter);
                //progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                //progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }


}

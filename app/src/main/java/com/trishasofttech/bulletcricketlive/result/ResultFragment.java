package com.trishasofttech.bulletcricketlive.result;

import android.os.Bundle;
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
import com.trishasofttech.bulletcricketlive.AllNewsDetailsApdater;
import com.trishasofttech.bulletcricketlive.AllNewsHelper;
import com.trishasofttech.bulletcricketlive.R;

public class ResultFragment extends Fragment {

    private ResultModel homeViewModel;
    private RecyclerView recyclerView;
    private ResultApdater resultApdater;
    private String allnews = "http://searchkero.com/cricket/result.json";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(ResultModel.class);
        View v = inflater.inflate(R.layout.base_layout, container, false);
        // Inflate the layout for this fragment
        recyclerView = v.findViewById(R.id.recycler_upcoming);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadresult();
        return v;
    }

    private void loadresult() {
        StringRequest request = new StringRequest(allnews, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ResultHelper parse = new ResultHelper(response);
                parse.upcominghelper();
                resultApdater  = new ResultApdater(getActivity(), ResultHelper.title, ResultHelper.team1,
                        ResultHelper.team2, ResultHelper.team1logo,ResultHelper.team2logo,ResultHelper.team1score,
                        ResultHelper.team2score,ResultHelper.result);
                recyclerView.setAdapter(resultApdater);

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

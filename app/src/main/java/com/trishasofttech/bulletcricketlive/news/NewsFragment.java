package com.trishasofttech.bulletcricketlive.news;

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
import com.trishasofttech.bulletcricketlive.AllNewsDetailsApdater;
import com.trishasofttech.bulletcricketlive.AllNewsHelper;
import com.trishasofttech.bulletcricketlive.R;


/*
public class NewsFragment extends BaseArticlesFragment {

    private static final String LOG_TAG = NewsFragment.class.getName();

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        String sportUrl = NewsPreferences.getPreferredUrl(getContext(), getString(R.string.sport));
        Log.e(LOG_TAG, sportUrl);

        // Create a new loader for the given URL
        return new NewsLoader(getActivity(), sportUrl);
    }
}
*/

public class NewsFragment extends Fragment {

    private RecyclerView recyclerView;
    private String allnews = "http://searchkero.com/cricket/latestnews.json";
    AllNewsDetailsApdater adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.base_layout, container, false);
        // Inflate the layout for this fragment
        recyclerView = v.findViewById(R.id.recycler_upcoming);
        recyclerView.setHasFixedSize(true);
        //to use RecycleView, you need a layout manager. default is LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadlatestnews();
        return v;
    }

    private void loadlatestnews() {
        StringRequest request = new StringRequest(allnews, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                AllNewsHelper parse = new AllNewsHelper(response);
                parse.upcominghelper();
                adapter  = new AllNewsDetailsApdater(getActivity(), AllNewsHelper.title, AllNewsHelper.desc,
                        AllNewsHelper.date, AllNewsHelper.url, AllNewsHelper.id);
                recyclerView.setAdapter(adapter);

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
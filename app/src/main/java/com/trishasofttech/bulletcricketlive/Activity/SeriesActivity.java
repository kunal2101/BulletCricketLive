package com.trishasofttech.bulletcricketlive.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trishasofttech.bulletcricketlive.R;
import com.trishasofttech.bulletcricketlive.SeriesAdapter;
import com.trishasofttech.bulletcricketlive.SeriesHelper;

public class SeriesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private String series_url;
    int seriesid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        getSupportActionBar().setTitle("Matches Series");
        Intent intent = getIntent();
        seriesid = intent.getIntExtra("seriesid", 1);
        //Toast.makeText(this, String.valueOf(seriesid), Toast.LENGTH_SHORT).show();
        recyclerView = findViewById(R.id.recycler_upcoming);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        if (seriesid ==1)
        {
            series_url = "http://searchkero.com/cricket/firstseries.json";
            loadseries();
        }
        else if (seriesid ==2)
        {
            series_url = "http://searchkero.com/cricket/secondseries.json";
            loadseries();
        }
        else if (seriesid ==3)
        {
            series_url = "http://searchkero.com/cricket/thirdseries.json";
            loadseries();
        }
        else if (seriesid ==4)
        {
            series_url = "http://searchkero.com/cricket/fourthseries.json";
            loadseries();
        }
        else if (seriesid ==5)
        {
            series_url = "http://searchkero.com/cricket/fifthseries.json";
            loadseries();
        }



    }

    private void loadseries() {
        StringRequest request = new StringRequest(series_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                SeriesHelper parse = new SeriesHelper(response);
                parse.serieshelper();

                SeriesAdapter adapter = new SeriesAdapter(getApplicationContext(), SeriesHelper.name, SeriesHelper.date,
                        SeriesHelper.id, seriesid, SeriesHelper.matchtime, SeriesHelper.gname, SeriesHelper.place, SeriesHelper.series);
                recyclerView.setAdapter(adapter);
                //progressBar.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                // pd.dismiss();
                //progressBar.setVisibility(View.GONE);

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
}

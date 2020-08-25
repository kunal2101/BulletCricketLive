package com.trishasofttech.bulletcricketlive.live;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trishasofttech.bulletcricketlive.R;
import com.trishasofttech.bulletcricketlive.livetabs.LiveDetailsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.MyNoteHolder> {
    Context context;
    ArrayList<HashMap<String,String>> ArrayListSeries;
    private String[] match;
    private String[] matchtype;
    private String[] matchvenue;
    private String[] matchseries;
    private String[] matchdate;
    private String[] team2logo;
    private long[] id;
    String url = "http://cricapi.com/api/cricketScore?apikey=QU2rB4akb2SbwAmUFTwtZQ8whBX2&unique_id=";
    String data;
    public LiveAdapter(Context context, String[] match, long[] id,String[] matchseries,ArrayList ArrayListSeries) {
        this.context = context;
        this.id = id;
        this.match=match;
        this.matchseries = matchseries;
        this.ArrayListSeries = ArrayListSeries;
    }

    @Override
    public MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_live, parent, false);
        return new MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyNoteHolder holder, final int position) {
      //Toast.makeText ( context,match.toString (),Toast.LENGTH_LONG ).show ();
      final HashMap<String,String > hash = ArrayListSeries.get (  position);
        holder.noteName.setText(hash.get ( "matchseries" ));
        holder.tv_team1.setText(hash.get ( "aTeam" ));
        holder.tv_team2.setText(hash.get ( "bTeam" ));
        holder.tv_team1score.setText(hash.get ( "a_1" ));
        holder.tv_team2score.setText(hash.get ( "b_1" ));

        holder.cardlive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent live = new Intent(context, LiveDetailsActivity.class);
                live.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                live.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                /*SharedPreferences sp= context.getSharedPreferences("live", 0);
                SharedPreferences.Editor ed = sp.edit();
                ed.putLong("id", id[position]);
                ed.putString("match", String.valueOf(match[position]));
                Toast.makeText(context, String.valueOf(match[position]), Toast.LENGTH_SHORT).show();
                ed.commit();
                */
                live.putExtra ( "key",hash.get("key") );
                live.putExtra ( "format", hash.get ( "format" ));
                context.startActivity(live);


            }
        });
    }




    @Override
    public int getItemCount() {
        return ArrayListSeries.size ();
    }

    class MyNoteHolder extends RecyclerView.ViewHolder {
        TextView noteName, notedate, tv_team1, tv_team2,team1_market,team2_market,tv_team1score, tv_team2score;
        ImageView iv_team1, iv_team2;
        CardView cardlive;

        public MyNoteHolder(View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.tv_title);
            notedate = itemView.findViewById(R.id.tv_date);
            iv_team1 = itemView.findViewById(R.id.iv_team1);
            iv_team2 = itemView.findViewById(R.id.iv_team2);
            tv_team1 = itemView.findViewById(R.id.tv_team1);
            tv_team2 = itemView.findViewById(R.id.tv_team2);
            team1_market = itemView.findViewById(R.id.team1_market);
            team2_market = itemView.findViewById(R.id.team2_market);
            tv_team1score = itemView.findViewById(R.id.tv_team1score);
            tv_team2score = itemView.findViewById(R.id.tv_team2score);
            cardlive = itemView.findViewById(R.id.card_live);
        }
    }
}
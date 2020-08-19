package com.trishasofttech.bulletcricketlive.result;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trishasofttech.bulletcricketlive.Activity.DetailsNewsActivity;
import com.trishasofttech.bulletcricketlive.R;

public class ResultApdater extends RecyclerView.Adapter<ResultApdater.MyNoteHolder> {
    Context context;
    private String[] title;
    private String[] team1;
    private String[] team2;
    private String[] team1logo;
    private String[] team2logo;
    private String[] team1score;
    private String[] team2score;
    private String[] result;
    private long[] id;


    public ResultApdater(Context context, String[] title, String[] team1, String[] team2, String[] team1logo,
                         String[] team2logo, String[] team1score, String[] team2score, String[] result) {
        this.context = context;
        this.title = title;
        this.team1 = team1;
        this.team2 = team2;
        this.team1logo = team1logo;
        this.team2logo = team2logo;
        this.team1score = team1score;
        this.team2score = team2score;
        this.result = result;
    }

    @Override
    public ResultApdater.MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        return new ResultApdater.MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultApdater.MyNoteHolder holder, final int position) {

        holder.tv_title.setText(title[position]);
        holder.result.setText(result[position]);
        holder.team2score.setText(team2score[position]);
        holder.team1score.setText(team1score[position]);
        holder.team1.setText(team1[position]);
        holder.team2.setText(team2[position]);
        Glide.with(context).load(team1logo[position]).into(holder.team1logo);
        Glide.with(context).load(team2logo[position]).into(holder.team2logo);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyNoteHolder extends RecyclerView.ViewHolder {
        TextView tv_title, team1, team2, team1score, team2score, result;
        ImageView team1logo, team2logo;

        public MyNoteHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            //tv_desc=itemView.findViewById(R.id.tv_desc);
            team1 = itemView.findViewById(R.id.tv_team1);
            team2 = itemView.findViewById(R.id.tv_team2);
            //book_layout = itemView.findViewById(R.id.book_layout);
            team1logo = itemView.findViewById(R.id.team1logo);
            team2logo = itemView.findViewById(R.id.team2logo);
            team1score = itemView.findViewById(R.id.team1score);
            team2score = itemView.findViewById(R.id.team2score);
            result = itemView.findViewById(R.id.result);
        }
    }


}
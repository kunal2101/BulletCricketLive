package com.trishasofttech.bulletcricketlive.upcoming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.trishasofttech.bulletcricketlive.R;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyNoteHolder> {
    Context context;
    private String[] name;
    private String[] date;
    private String[] team1logo;
    private String[] team2logo;
    private String[] location;
    private String[] ground;
    private String[] time;

    private long[] id;


    public UpcomingAdapter(Context context, String[] name, String[] date, long[] id, String [] team1logo,
                           String [] team2logo, String [] location, String [] ground, String [] time) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.id = id;
        this.location=location;
        this.ground = ground;
        this.team1logo = team1logo;
        this.team2logo = team2logo;
        this.time  = time;
    }

    @Override
    public MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_upcoming, parent, false);
        return new MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNoteHolder holder, final int position) {


        //holder.notedate.setText(String.valueOf(id[position]));
        holder.notedate.setText(date[position]+" "+time[position]);
        String title = name[position];
        String mtitle = title.replace(",", "\n");
        holder.noteName.setText(mtitle);
        /*to set the Team logo*/
        Glide.with(context).load(team1logo[position]).into(holder.iv_team1);
        Glide.with(context).load(team2logo[position]).into(holder.iv_team2);
        holder.tv_ground.setText("Ground :-"+ground[position]);
        holder.tv_location.setText("Location :-"+location[position]);
        //holder.tv_time.setText(time[position]);


        /*holder.book_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url[position]));
                context.startActivity(intent);
            }
        });*/


        /*holder.book_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url[position]));
                context.startActivity(intent);
            }
        });*/
        /*holder.book_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url[position]));
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class MyNoteHolder extends RecyclerView.ViewHolder {
        TextView noteName, notedate, tv_time, tv_location,tv_ground;
        ImageView iv_team1, iv_team2;

        //ConstraintLayout book_layout;
        public MyNoteHolder(View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.tv_title);
            notedate = itemView.findViewById(R.id.tv_date);
            iv_team1 = itemView.findViewById(R.id.iv_team1);
            iv_team2 = itemView.findViewById(R.id.iv_team2);
            //tv_time = itemView.findViewById(R.id.tv_time);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_ground = itemView.findViewById(R.id.tv_ground);
            //book_layout = itemView.findViewById(R.id.book_layout);
        }
    }

    public void clear() {

        notifyItemRangeRemoved(0, name.length);
    }

}
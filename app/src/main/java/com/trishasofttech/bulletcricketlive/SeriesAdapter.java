package com.trishasofttech.bulletcricketlive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyHolder>{
    Context context;
    private String[] name;
    private String[] date;
    private String[] gname;
    private String[] place;
    private String[] matchtime;
    private String[] series;
    private int[] id;
    int seriesid;


    public SeriesAdapter(Context context, String[] name, String[] date, int[] id, int seriesid, String[] matchtime,
                         String [] gname, String [] place, String [] series) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.matchtime = matchtime;
        this.gname=gname;
        this.place = place;
        this.id = id;
        this.series = series;
        this.seriesid = seriesid;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_detailseries,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {


        holder.notedate.setText("Date: "+date[position]+" | Time: "+matchtime[position]);
        holder.noteName.setText(name[position]);

        holder.gname.setText(gname[position]);
        holder.place.setText(place[position]);
        holder.tv_series.setText(series[position]);


        //holder.notedate.setText(String.valueOf(id[position]));
        //holder.notedate.setText(date[position]);
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
        return series.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView noteName,notedate, gname, place, tv_series;
        //ConstraintLayout book_layout;
        public MyHolder(View itemView) {
            super(itemView);
            noteName=itemView.findViewById(R.id.tv_title);
            notedate=itemView.findViewById(R.id.tv_date);
            gname=itemView.findViewById(R.id.gname);
            place=itemView.findViewById(R.id.place);
            tv_series = itemView.findViewById(R.id.tv_series);

            //book_layout = itemView.findViewById(R.id.book_layout);
        }
    }
}
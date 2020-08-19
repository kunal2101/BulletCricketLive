package com.trishasofttech.bulletcricketlive.Fragment.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.trishasofttech.bulletcricketlive.Activity.SeriesActivity;
import com.trishasofttech.bulletcricketlive.R;


public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyNoteHolder>{
    Context context;
    private String[] name;
    private String[] date;
    private long[] id;

    public SeriesAdapter(Context context, String[] name, String[] date, long[] id) {
        this.context = context;
        this.name = name;
        this.date = date;
        this.id = id;
    }

    @Override
    public MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_series,parent,false);
        return new MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(MyNoteHolder holder, final int position) {
        holder.notedate.setText(date[position]);
        String title = name[position];
        String mtitle= title.replace(",", "\n");
        holder.noteName.setText(mtitle);

        holder.btn_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (position)
                {
                    case 0:
                        Intent intent = new Intent(context, SeriesActivity.class);
                        intent.putExtra("seriesid", 1);
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(context, SeriesActivity.class);
                        intent1.putExtra("seriesid", 2);
                        context.startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(context, SeriesActivity.class);
                        intent2.putExtra("seriesid", 3);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(context, SeriesActivity.class);
                        intent3.putExtra("seriesid", 4);
                        context.startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(context, SeriesActivity.class);
                        intent4.putExtra("seriesid", 5);
                        context.startActivity(intent4);
                        break;
                    default:
                }

            }
        });
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

    class MyNoteHolder extends RecyclerView.ViewHolder{
        TextView noteName,notedate;
        Button btn_series;
        //ConstraintLayout book_layout;
        public MyNoteHolder(View itemView) {
            super(itemView);
            noteName=itemView.findViewById(R.id.tv_title);
            notedate=itemView.findViewById(R.id.tv_date);
            btn_series = itemView.findViewById(R.id.btn_series);
            //book_layout = itemView.findViewById(R.id.book_layout);
        }
    }
}
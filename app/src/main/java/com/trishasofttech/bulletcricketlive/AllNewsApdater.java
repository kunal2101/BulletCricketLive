package com.trishasofttech.bulletcricketlive;

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

public class AllNewsApdater extends RecyclerView.Adapter<AllNewsApdater.MyNoteHolder>{
    Context context;
    private String[] title;
    private String[] date;
    private String[] url;
    private String[] description;
    private long[] id;


    public AllNewsApdater(Context context, String[] title, String[] description , String[] date , String[] url, long[] id) {
        this.context = context;
        this.title = title;
        this.description = description;
        this.date = date;
        this.url = url;
        this.id = id;
    }

    @Override
    public AllNewsApdater.MyNoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_topnews,parent,false);
        return new AllNewsApdater.MyNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(AllNewsApdater.MyNoteHolder holder, final int position) {


        //holder.notedate.setText(String.valueOf(id[position]));
       /* if (position==0)
        {
            holder.tv_title.setText(title[position]);
            ViewGroup.LayoutParams params = holder.card_view.getLayoutParams();
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.height = 500;
            holder.card_view.setLayoutParams(params);
            holder.card_image.setBackgroundResource(R.drawable.australia);
            Glide.with(context).load(url[position]).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_url);

            *//*ViewGroup.LayoutParams params = holder.iv_url.getLayoutParams();
            params.width = 1200;
            params.height = 500;
            holder.iv_url.setLayoutParams(params);*//*
            holder.tv_newsdatemsg.setVisibility(View.GONE);
            holder.tv_date.setVisibility(View.GONE);
            holder.iv_newshare.setVisibility(View.GONE);
            *//*click on the cardview*//*
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent news = new Intent(context, DetailsNewsActivity.class);

                    news.putExtra("title", title[position]);
                    news.putExtra("desc", description[position]);
                    news.putExtra("date", date[position]);
                    news.putExtra("url", url[position]);

                    news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    news.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(news);
                }
            });
        }*/

            holder.tv_title.setText(title[position]);
            holder.tv_date.setText(date[position]);
            Glide.with(context).load(url[position]).into(holder.iv_url);

            /*click on the cardview*/
            holder.card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent news = new Intent(context, DetailsNewsActivity.class);

                    news.putExtra("title", title[position]);
                    news.putExtra("desc", description[position]);
                    news.putExtra("date", date[position]);
                    news.putExtra("url", url[position]);

                    news.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    news.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(news);
                }
            });



    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class MyNoteHolder extends RecyclerView.ViewHolder{
        TextView tv_title,tv_date, tv_desc, tv_newsdatemsg;
        ImageView iv_url, iv_team2, iv_newshare;
        //ConstraintLayout book_layout;
        CardView card_view;
        RelativeLayout card_image;
        public MyNoteHolder(View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.tv_newstitle);
            //tv_desc=itemView.findViewById(R.id.tv_desc);
            iv_url= itemView.findViewById(R.id.iv_news);
            tv_date = itemView.findViewById(R.id.tv_newsdate);
            //book_layout = itemView.findViewById(R.id.book_layout);
            card_view = itemView.findViewById(R.id.card_view);
            tv_newsdatemsg = itemView.findViewById(R.id.tv_newsdatemsg);
            iv_newshare = itemView.findViewById(R.id.iv_newsshare);
            card_image = itemView.findViewById(R.id.card_image);
        }
    }


}
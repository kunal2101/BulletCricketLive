package com.trishasofttech.bulletcricketlive.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.trishasofttech.bulletcricketlive.R;

public class DetailsNewsActivity extends AppCompatActivity {
ImageView imageView;
TextView tv_title, tv_desc, tv_date;
String title, desc, date, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detailsnews);
        imageView = findViewById(R.id.iv_news);
        tv_date = findViewById(R.id.tv_newsdate);
        tv_desc = findViewById(R.id.tv_newsdesc);
        tv_title = findViewById(R.id.tv_newstitle);

        Intent news = getIntent();
        title = news.getStringExtra("title");
        date = news.getStringExtra("date");
        desc = news.getStringExtra("desc");
        url = news.getStringExtra("url");

        Glide.with(this).load(url).into(imageView);
        tv_title.setText(title);
        tv_desc.setText(desc);
        tv_date.setText(date);
    }
}

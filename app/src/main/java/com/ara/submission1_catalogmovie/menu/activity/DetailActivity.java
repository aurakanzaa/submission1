package com.ara.submission1_catalogmovie.menu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ara.submission1_catalogmovie.R;
import com.ara.submission1_catalogmovie.generator.ServiceGenerator;
import com.ara.submission1_catalogmovie.models.ResultsMovie;
import com.ara.submission1_catalogmovie.services.MovieServices;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private String img,jdl,rating,date,overvi;
    private TextView dTitle;
    private TextView dRating;
    private TextView dDate;
    private TextView dOverview;
    private ImageView dImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dImg = (ImageView) findViewById(R.id.img);
        dTitle = (TextView) findViewById(R.id.jdl);
        dRating = (TextView) findViewById(R.id.rating);
        dDate = (TextView) findViewById(R.id.yeartxt);
        dOverview = (TextView) findViewById(R.id.overviewtxt);

        // ijo2 harus sama dengan yang ada di movie adapter agar dapat dipanggil
        Intent intent = getIntent();
        img = intent.getStringExtra("poster");
        jdl = intent.getStringExtra("judul");
        overvi = intent.getStringExtra("overview");
        date = intent.getStringExtra("releasedate");
        rating = intent.getStringExtra("rating");


        Glide.with(this).load(img).error(R.drawable.ic_launcher_background).into(dImg);
        dTitle.setText(jdl);
        dDate.setText(date);
        dRating.setText(rating);

        dOverview.setText(overvi);

    }
}

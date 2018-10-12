package com.ara.submission1_catalogmovie.menu.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ara.submission1_catalogmovie.R;
import com.ara.submission1_catalogmovie.adapter.MovieAdapter;
import com.ara.submission1_catalogmovie.generator.ServiceGenerator;
import com.ara.submission1_catalogmovie.models.Movie;
import com.ara.submission1_catalogmovie.models.ResultsMovie;
import com.ara.submission1_catalogmovie.services.MovieServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private ArrayList<ResultsMovie> listResult;
    private RecyclerView recycler;
    private Button bSearch;
    private EditText eSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bSearch = (Button) findViewById(R.id.btnSearch);
        eSearch = (EditText) findViewById(R.id.search);
        recycler = (RecyclerView) findViewById(R.id.movies_recycler_view);

        listResult = new ArrayList<>();

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataMovies();
            }
        });
    }

    private void getDataMovies() {
        MovieServices apiService = ServiceGenerator.getInstanceRetrofit();
        apiService.getMovie(eSearch.getText().toString().trim())
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            listResult = response.body().getResults();
                            MovieAdapter adapter = new MovieAdapter(MainActivity.this, listResult);
                            recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            recycler.setAdapter(adapter);
                            Toast.makeText(MainActivity.this, "pencarian ditemukan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "tidak dapat menemukan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "cek koneksi", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
        if (item.getItemId() == R.id.action_change_settings) {
            Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


}

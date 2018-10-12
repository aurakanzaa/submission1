package com.ara.submission1_catalogmovie.menu.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ara.submission1_catalogmovie.R;
import com.ara.submission1_catalogmovie.adapter.MovieAdapter;
import com.ara.submission1_catalogmovie.generator.ServiceGenerator;
import com.ara.submission1_catalogmovie.menu.activity.MainActivity;
import com.ara.submission1_catalogmovie.models.Movie;
import com.ara.submission1_catalogmovie.models.ResultsMovie;
import com.ara.submission1_catalogmovie.services.MovieServices;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNowPlaying extends Fragment {

    private ArrayList<ResultsMovie> listResult;
    private RecyclerView recyclerNp;
    private Button bSearch;
    private EditText eSearch;
    private MovieAdapter movieAdapter;



    public FragmentNowPlaying() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        bSearch = (Button) view.findViewById(R.id.btnSearch);
        eSearch = (EditText) view.findViewById(R.id.search);
        recyclerNp = (RecyclerView) view.findViewById(R.id.movies_recycler_view);

        listResult = new ArrayList<>();

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataMovies();
            }
        });
        listResult = new ArrayList<>();
        Locale current = getResources().getConfiguration().locale;
        if (current.toString().equalsIgnoreCase("en_US")) {
            getMovieNowPlayingEN();
        } else {
            getMovieNowPlayingID();
        }

        return view;
    }

    private void getDataMovies() {
        MovieServices movieService = ServiceGenerator.getInstanceRetrofit();
        movieService.getMovie(eSearch.getText().toString().trim())
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if (response.isSuccessful()) {
                            listResult = response.body().getResults();
                            MovieAdapter adapter = new MovieAdapter(getActivity(), listResult);
                            recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerNp.setAdapter(adapter);
                            Toast.makeText(getActivity(), "pencarian ditemukan", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "tidak dapat menemukan", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        Toast.makeText(getActivity(), "cek koneksi", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void getMovieNowPlayingEN() {
        MovieServices apiService = ServiceGenerator.getInstanceRetrofit();
        apiService.getMovieNowPlaying("en_US").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    listResult = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), listResult);
                    recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerNp.setAdapter(movieAdapter);
//                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

                Toast.makeText(getActivity(), "" + "periksa koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMovieNowPlayingID() {
        MovieServices apiService = ServiceGenerator.getInstanceRetrofit();
        apiService.getMovieNowPlaying("id").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    listResult = response.body().getResults();
                    movieAdapter = new MovieAdapter(getActivity(), listResult);
                    recyclerNp.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerNp.setAdapter(movieAdapter);
//                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getActivity(), "" + "periksa koneksi anda", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

package com.ara.submission1_catalogmovie.services;

import com.ara.submission1_catalogmovie.BuildConfig;
import com.ara.submission1_catalogmovie.models.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieServices {
    @GET("movie?api_key=0fe8ca3c33e758e0001314d6ed0415fc&language=en-US")
    Call<Movie> getMovie(@Query("query") String query);

    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET(BuildConfig.NowPlayingEng)
    Call<Movie>getMovieNowPlaying(@Query("language") String language);
    @GET(BuildConfig.NowPlayingEng)
    Call<Movie>getMovieUpComing(@Query("language") String language);

}

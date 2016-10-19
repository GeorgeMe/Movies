package com.is.model.rest;

import com.is.movies.entities.ConfigurationResponse;
import com.is.movies.entities.ImagesWrapper;
import com.is.movies.entities.MovieDetail;
import com.is.movies.entities.MoviesWrapper;
import com.is.movies.entities.ReviewsWrapper;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Interface representing the MovieDatabaseAPI endpoints
 * used by retrofit
 */
public interface MovieDatabaseAPI {

    @GET("/movie/popular")
    void getPopularMovies(
            @Query("api_key") String apiKey,
            Callback<MoviesWrapper> callback);

    @GET("/movie/{id}")
    void getMovieDetail(
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<MovieDetail> callback
    );

    @GET("/movie/popular")
    void getPopularMoviesByPage(
            @Query("api_key") String apiKey,
            @Query("page") String page,
            Callback<MoviesWrapper> callback
    );

    @GET("/configuration")
    void getConfiguration(
            @Query("api_key") String apiKey,
            Callback<ConfigurationResponse> response
    );

    @GET("/movie/{id}/reviews")
    void getReviews(
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<ReviewsWrapper> response
    );

    @GET("/movie/{id}/images")
    void getImages(
            @Query("api_key") String apiKey,
            @Path("id") String movieId,
            Callback<ImagesWrapper> response
    );
}

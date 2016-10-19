package com.is.model.rest;
import android.content.Context;

import com.is.model.common.Constants;
import com.is.movies.entities.ConfigurationResponse;
import com.is.movies.entities.ImagesWrapper;
import com.is.movies.entities.MovieDetail;
import com.is.movies.entities.MoviesWrapper;
import com.is.movies.entities.ReviewsWrapper;
import com.squareup.otto.Bus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by George on 2015/8/13.
 */
public class RestMovieSource extends Retrofit2Movie implements RestDataSource {

    private  Bus bus;

    public RestMovieSource(Context context, Bus bus) {
        super(context);
        this.bus = bus;
    }

    @Override
    public void getMovies() {

        movieDatabaseAPI.getPopularMovies(Constants.API_KEY, retrofitCallback);
    }

    @Override
    public void getDetailMovie(String id) {

        movieDatabaseAPI.getMovieDetail(Constants.API_KEY, id,
                retrofitCallback);
    }

    @Override
    public void getReviews(String id) {

        movieDatabaseAPI.getReviews(Constants.API_KEY, id,
                retrofitCallback);
    }

    @Override
    public void getConfiguration() {

        movieDatabaseAPI.getConfiguration(Constants.API_KEY, retrofitCallback);
    }

    @Override
    public void getImages(String movieId) {

        movieDatabaseAPI.getImages(Constants.API_KEY, movieId,
                retrofitCallback);
    }

    @Override
    public void getMoviesByPage(int page) {

        movieDatabaseAPI.getPopularMoviesByPage(
                Constants.API_KEY,
                page + "",
                retrofitCallback
        );
    }
    public Callback retrofitCallback=new Callback() {
        @Override
        public void onResponse(Call call, Response response) {

            if (response.body() instanceof MovieDetail) {

                MovieDetail detailResponse = (MovieDetail) response.body();
                bus.post(detailResponse);

            } else if (response.body() instanceof MoviesWrapper) {

                MoviesWrapper moviesApiResponse = (MoviesWrapper) response.body();
                bus.post(moviesApiResponse);

            } else if (response.body() instanceof ConfigurationResponse) {

                ConfigurationResponse configurationResponse = (ConfigurationResponse) response.body();
                bus.post(configurationResponse);

            } else if (response.body() instanceof ReviewsWrapper) {

                ReviewsWrapper reviewsWrapper = (ReviewsWrapper) response.body();
                bus.post(reviewsWrapper);

            } else if (response.body() instanceof ImagesWrapper) {

                ImagesWrapper imagesWrapper = (ImagesWrapper) response.body();
                bus.post(imagesWrapper);
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            System.out.printf("[DEBUG] RestMovieSource failure - " + t.getMessage());
        }
    };
}

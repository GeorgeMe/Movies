package com.is.model.rest;
import com.is.model.common.Constants;
import com.is.model.entities.ConfigurationResponse;
import com.is.model.entities.ImagesWrapper;
import com.is.model.entities.MovieDetail;
import com.is.model.entities.MoviesWrapper;
import com.is.model.entities.ReviewsWrapper;
import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
/**
 * Created by George on 2015/8/13.
 */
public class RestMovieSource implements RestDataSource {

    private final MovieDatabaseAPI moviesDBApi;
    private final Bus bus;

    public RestMovieSource(Bus bus) {

        RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(Constants.MOVIE_DB_HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        moviesDBApi = movieAPIRest.create(MovieDatabaseAPI.class);
        this.bus = bus;
    }

    @Override
    public void getMovies() {

        moviesDBApi.getPopularMovies(Constants.API_KEY, retrofitCallback);
    }

    @Override
    public void getDetailMovie(String id) {

        moviesDBApi.getMovieDetail(Constants.API_KEY, id,
                retrofitCallback);
    }

    @Override
    public void getReviews(String id) {

        moviesDBApi.getReviews(Constants.API_KEY, id,
                retrofitCallback);
    }

    @Override
    public void getConfiguration() {

        moviesDBApi.getConfiguration(Constants.API_KEY, retrofitCallback);
    }

    @Override
    public void getImages(String movieId) {

        moviesDBApi.getImages(Constants.API_KEY, movieId,
                retrofitCallback);
    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {

            if (o instanceof MovieDetail) {

                MovieDetail detailResponse = (MovieDetail) o;
                bus.post(detailResponse);

            } else if (o instanceof MoviesWrapper) {

                MoviesWrapper moviesApiResponse = (MoviesWrapper) o;
                bus.post(moviesApiResponse);

            } else if (o instanceof ConfigurationResponse) {

                ConfigurationResponse configurationResponse = (ConfigurationResponse) o;
                bus.post(configurationResponse);

            } else if (o instanceof ReviewsWrapper) {

                ReviewsWrapper reviewsWrapper = (ReviewsWrapper) o;
                bus.post(reviewsWrapper);

            } else if (o instanceof ImagesWrapper) {

                ImagesWrapper imagesWrapper = (ImagesWrapper) o;
                bus.post(imagesWrapper);
            }
        }

        @Override
        public void failure(RetrofitError error) {

            System.out.printf("[DEBUG] RestMovieSource failure - " + error.getMessage());
        }
    };

    @Override
    public void getMoviesByPage(int page) {

        moviesDBApi.getPopularMoviesByPage(
                Constants.API_KEY,
                page + "",
                retrofitCallback
        );
    }
}

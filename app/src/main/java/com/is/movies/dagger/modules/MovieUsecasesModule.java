package com.is.movies.dagger.modules;
import com.is.domain.GetMovieDetailUsecase;
import com.is.domain.GetMovieDetailUsecaseController;
import com.is.model.rest.RestMovieSource;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
/**
 * Created by George on 2015/8/13.
 */

@Module
public class MovieUsecasesModule {

    private final String movieId;

    public MovieUsecasesModule(String movieId) {

        this.movieId = movieId;
    }

    @Provides GetMovieDetailUsecase provideGetMovieDetailUsecase (Bus bus, RestMovieSource movieSource) {
        return new GetMovieDetailUsecaseController(movieId, bus, movieSource);
    }
}

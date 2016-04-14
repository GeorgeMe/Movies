package com.is.movies.dagger.modules;
import com.is.domain.ConfigurationUsecase;
import com.is.domain.ConfigurationUsecaseController;
import com.is.domain.GetMoviesUsecase;
import com.is.domain.GetMoviesUsecaseController;
import com.is.model.rest.RestMovieSource;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
/**
 * Created by George on 2015/8/13.
 */

@Module
public class BasicMoviesUsecasesModule {

    @Provides ConfigurationUsecase provideConfigurationUsecase (Bus bus, RestMovieSource moviesSource) {
        return new ConfigurationUsecaseController(moviesSource, bus);
    }

    @Provides GetMoviesUsecase provideMoviesUsecase (Bus bus, RestMovieSource movieSource) {
        return new GetMoviesUsecaseController(movieSource, bus);
    }
}

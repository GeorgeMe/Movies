package com.is.movies.dagger.modules;
import com.is.domain.ConfigurationUsecase;
import com.is.domain.ConfigurationUsecaseController;
import com.is.model.rest.RestMovieSource;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;
/**
 * Created by George on 2015/8/13.
 */
@Module
public class UsecaseModule {

    @Provides
    ConfigurationUsecase provideConfigurationUsecase (Bus bus, RestMovieSource restMovieSource) {
        return new ConfigurationUsecaseController(restMovieSource, bus);
    }
}

package com.is.movies.dagger.components;
import com.is.movies.dagger.modules.ApplicationModule;
import com.is.movies.dagger.modules.DomainModule;
import com.is.model.rest.RestMovieSource;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by George on 2015/8/13.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        DomainModule.class,
})

public interface AppComponent {

    Bus bus();
    RestMovieSource restMovieSource();
}

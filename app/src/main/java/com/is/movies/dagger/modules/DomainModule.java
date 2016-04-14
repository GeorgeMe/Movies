package com.is.movies.dagger.modules;
import com.is.model.rest.RestMovieSource;
import com.squareup.otto.Bus;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
/**
 * Created by George on 2015/8/13.
 */
@Module
public class DomainModule {

    @Provides @Singleton Bus provideBus () {
        return new Bus();
    }
    @Provides @Singleton RestMovieSource provideDataSource (Bus bus) { return new RestMovieSource(bus); }

}

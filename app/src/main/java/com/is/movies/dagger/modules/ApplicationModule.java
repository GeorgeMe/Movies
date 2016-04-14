package com.is.movies.dagger.modules;
import android.content.Context;

import com.is.movies.MoviesApp;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
/**
 * Created by George on 2015/8/13.
 */
@Module
public class ApplicationModule {

    private final MoviesApp application;

    public ApplicationModule(MoviesApp application) {

        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext () { return application; }

    public static class MovieUsecasesModule {
    }
}

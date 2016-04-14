package com.is.movies;
import android.app.Application;
import com.is.movies.dagger.components.AppComponent;
import com.is.movies.dagger.components.DaggerAppComponent;
import com.is.movies.dagger.modules.ApplicationModule;
import com.is.movies.dagger.modules.DomainModule;

/**
 * Created by George on 2015/8/13.
 */
public class MoviesApp extends Application {

    private AppComponent mAppComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeDependencyInjector();
    }

    private void initializeDependencyInjector() {

        mAppComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .domainModule(new DomainModule())
                .build();
    }

    public AppComponent getAppComponent() {

        return mAppComponent;
    }
}

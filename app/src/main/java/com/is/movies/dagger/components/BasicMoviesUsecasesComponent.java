package com.is.movies.dagger.components;
import com.is.movies.dagger.modules.BasicMoviesUsecasesModule;
import com.is.movies.dagger.scopes.PerActivity;
import com.is.movies.views.activities.MoviesActivity;

import dagger.Component;
/**
 * Created by George on 2015/8/13.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = BasicMoviesUsecasesModule.class)
public interface BasicMoviesUsecasesComponent {

    void inject(MoviesActivity moviesActivity);
}

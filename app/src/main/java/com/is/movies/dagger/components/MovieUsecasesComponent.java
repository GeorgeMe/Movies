package com.is.movies.dagger.components;
import com.is.movies.dagger.modules.MovieUsecasesModule;
import com.is.movies.dagger.scopes.PerActivity;
import com.is.movies.views.activities.MovieDetailActivity;

import dagger.Component;
/**
 * Created by George on 2015/8/13.
 */

@PerActivity @Component(dependencies = AppComponent.class, modules = MovieUsecasesModule.class)
public interface MovieUsecasesComponent {

    void inject(MovieDetailActivity movieDetailActivity);
}

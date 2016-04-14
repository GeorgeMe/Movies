package com.is.movies.mvp.presenters;
import com.is.movies.mvp.views.MoviesView;
import com.is.model.common.Constants;
import com.is.domain.ConfigurationUsecase;
import com.is.domain.GetMoviesUsecase;
import com.is.model.entities.MoviesWrapper;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;
/**
 * Created by George on 2015/8/13.
 */
public class MoviesPresenter extends Presenter {

    private final Bus mBus;
    private ConfigurationUsecase mConfigureUsecase;
    private GetMoviesUsecase mGetPopularShows;
    private MoviesView mMoviesView;

    private boolean isLoading = false;
    private boolean mRegistered;

    @Inject
    public MoviesPresenter(ConfigurationUsecase configurationUsecase, GetMoviesUsecase getMoviesUsecase, Bus bus) {

        mConfigureUsecase   = configurationUsecase;
        mGetPopularShows    = getMoviesUsecase;
        mBus = bus;
    }

    public void attachView (MoviesView moviesView) {

        mMoviesView = moviesView;
    }

    @Subscribe
    public void onPopularMoviesReceived(MoviesWrapper moviesWrapper) {

        if (mMoviesView.isTheListEmpty()) {

            mMoviesView.hideLoading();
            mMoviesView.showMovies(moviesWrapper.getResults());

        } else {

            mMoviesView.hideActionLabel();
            mMoviesView.appendMovies(moviesWrapper.getResults());
        }

        isLoading = false;
    }

    @Subscribe
    public void onConfigurationFinished (String baseImageUrl) {

        Constants.BASIC_STATIC_URL = baseImageUrl;
        mGetPopularShows.execute();
    }

    public void onEndListReached () {

        mGetPopularShows.execute();
        mMoviesView.showLoadingLabel ();
        isLoading = true;
    }

    @Override
    public void start() {

        if (mMoviesView.isTheListEmpty()) {

            mBus.register(this);
            mRegistered = true;

            mMoviesView.showLoading();
            mConfigureUsecase.execute();
        }
    }

    @Override
    public void stop() {
    }

    public boolean isLoading() {

        return isLoading;
    }

    public void setLoading(boolean isLoading) {

        this.isLoading = isLoading;
    }
}
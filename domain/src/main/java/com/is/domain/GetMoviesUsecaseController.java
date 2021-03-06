package com.is.domain;
import com.is.movies.entities.MoviesWrapper;
import com.is.model.rest.RestDataSource;
import com.squareup.otto.Bus;

import javax.inject.Inject;
/**
 * Created by George on 2015/8/13.
 */
public class GetMoviesUsecaseController implements GetMoviesUsecase {

    private final RestDataSource mDataSource;
    private final Bus mUiBus;
    private int mCurrentPage = 1;

    /**
     * Constructor of the class.
     *
     * @param uiBus The bus to communicate the domain module and the app module
     * @param dataSource The data source to retrieve the list of movies
     */
    @Inject
    public GetMoviesUsecaseController(RestDataSource dataSource, Bus uiBus) {

        mDataSource = dataSource;
        mUiBus = uiBus;

        mUiBus.register(this);
    }

    @Override
    public void requestPopularMovies() {

        mDataSource.getMoviesByPage(mCurrentPage);
    }

    @Override
    public void sendMoviesToPresenter (MoviesWrapper response) {

        mUiBus.post(response);
    }

    @Override
    public void unRegister() {

        mUiBus.unregister(this);
    }

    @Override
    public void execute() {

        requestPopularMovies();
        mCurrentPage++;
    }
}

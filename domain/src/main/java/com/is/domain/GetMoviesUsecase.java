package com.is.domain;
import com.is.model.entities.MoviesWrapper;
/**
 * Created by George on 2015/8/13.
 */
@SuppressWarnings("UnusedDeclaration")
public interface GetMoviesUsecase extends Usecase {


    /**
     * Request datasource the most popular movies
     */
    public void requestPopularMovies();

    /**
     * Sends the PopularMoviesApiResponse thought the communication system
     * to be received by the presenter in another module
     *
     * @param response the response containing a list with movies
     */
    public void sendMoviesToPresenter(MoviesWrapper response);

    public void unRegister();
}

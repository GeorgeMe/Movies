package com.is.model.rest;

import com.is.model.MediaDataSource;

/**
 * Created by George on 2015/8/13.
 */
public interface RestDataSource extends MediaDataSource {

    public void getMoviesByPage(int page);
}

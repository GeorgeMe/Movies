package com.is.movies.mvp.interactor;

import android.content.Context;

import com.is.movies.entities.ConfigurationResponse;
import com.is.movies.base.Retrofit2Movie;
import com.is.movies.mvp.listeners.BaseSingleLoadedListener;
import com.is.movies.mvp.listeners.CommonSingleInteractor;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by George on 2016/7/21.
 */
public class ConfigurationInteractor extends Retrofit2Movie implements CommonSingleInteractor {

    private BaseSingleLoadedListener<ConfigurationResponse> loadedListener;

    public ConfigurationInteractor(Context context, BaseSingleLoadedListener<ConfigurationResponse> loadedListener) {
        super(context);
        this.loadedListener = loadedListener;
    }

    @Override
    public void getCommonSingleData(JSONObject json) {
        Call<ConfigurationResponse> call=movieAPI.getConfiguration();
        call.enqueue(new Callback<ConfigurationResponse>() {
            @Override
            public void onResponse(Call<ConfigurationResponse> call, Response<ConfigurationResponse> response) {
                loadedListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ConfigurationResponse> call, Throwable t) {
                loadedListener.onFailure(t.getMessage());
            }
        });
    }
}

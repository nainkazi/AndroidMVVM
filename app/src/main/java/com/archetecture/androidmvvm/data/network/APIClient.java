package com.archetecture.androidmvvm.data.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nain Kazi on 4/24/2019.
 */

public class APIClient {

    private static final String BASE_URL ="https://api.myjson.com/";

    private static Retrofit getRetroInstance()
    {
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

    }

    public static APIService getAPIService()
    {
        return getRetroInstance().create(APIService.class);
    }
}

package com.archetecture.androidmvvm.data.network;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Nain Kazi on 4/24/2019.
 */

public interface APIService {

    @GET("bins/nl6jh/")
    Call<JsonObject> getNewsFeeds();

}

package com.archetecture.androidmvvm.ui.news_feeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import android.content.Context;

import com.archetecture.androidmvvm.data.models.NewsEntity;
import com.archetecture.androidmvvm.data.network.APIClient;
import com.archetecture.androidmvvm.data.network.APIService;
import com.archetecture.androidmvvm.utils.Constants;
import com.archetecture.androidmvvm.utils.NewsResponseDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nain Kazi on 4/24/2019.
 */

public class NewsRepositry {

    Context context;
    MutableLiveData<List<NewsEntity>> listLiveData = new MutableLiveData<>();


    public NewsRepositry(Context context) {
        this.context = context;
    }

    public void getNewsFeeds() {
        APIService apiService = APIClient.getAPIService();
        Call<JsonObject> call = apiService.getNewsFeeds();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, final Response<JsonObject> response) {

                Type listType = new TypeToken<List<NewsEntity>>() {
                }.getType();
                Gson gson = new GsonBuilder().registerTypeAdapter(NewsEntity.class, new NewsResponseDeserializer()).create();
                JsonArray resultArray = response.body().getAsJsonArray(Constants.KEY_RESULT);
                List<NewsEntity> newsEntities = (List<NewsEntity>) gson.fromJson(resultArray.toString(), listType);
                listLiveData.setValue(newsEntities);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                 listLiveData.setValue(null);
            }
        });

    }

    public LiveData<List<NewsEntity>> getLiveData() {
        return listLiveData;
    }
}

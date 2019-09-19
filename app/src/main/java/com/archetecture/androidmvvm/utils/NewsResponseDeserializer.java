package com.archetecture.androidmvvm.utils;

import com.archetecture.androidmvvm.data.models.NewsEntity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class NewsResponseDeserializer implements JsonDeserializer<NewsEntity> {
    @Override
    public NewsEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jdc) throws JsonParseException {
        if (!(json.getAsJsonObject().get(Constants.KEY_MULTIMEDIA) instanceof JsonArray)) {
            json.getAsJsonObject().add(Constants.KEY_MULTIMEDIA, new JsonArray());
        }
        return new Gson().fromJson(json, NewsEntity.class);
    }
}

package com.archetecture.androidmvvm.ui.news_feeds;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;

import com.archetecture.androidmvvm.data.models.NewsEntity;

import java.util.List;

/**
 * Created by Nain Kazi on 4/24/2019.
 */

public class NewsViewModel extends ViewModel {

    Context context;
    NewsRepositry newsRepositry;
    LiveData<List<NewsEntity>> liveData = new MutableLiveData<>();

    public NewsViewModel(Context context) {
        this.context = context;
        this.newsRepositry = new NewsRepositry(context);
        this.liveData = newsRepositry.getLiveData();
    }

    public void getNewsList() {
        newsRepositry.getNewsFeeds();
    }

    public LiveData<List<NewsEntity>> getNewsListData() {
        context = context;
        newsRepositry = new NewsRepositry(context);
        return newsRepositry.getLiveData();
    }
}

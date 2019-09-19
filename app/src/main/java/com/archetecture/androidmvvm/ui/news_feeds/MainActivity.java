package com.archetecture.androidmvvm.ui.news_feeds;

import androidx.lifecycle.Observer;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.archetecture.androidmvvm.R;
import com.archetecture.androidmvvm.data.models.NewsEntity;
import com.archetecture.androidmvvm.ui.news_details.NewsDetails;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private NewsViewModel newsViewModel;
    private List<NewsEntity> newsEntitiesList;
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setObservable();
        setData();

    }

    private void setData() {
        swipeRefreshLayout.setRefreshing(true);
        newsViewModel.getNewsList();

    }

    private void setObservable() {
        newsViewModel.liveData.observe(this, new Observer<List<NewsEntity>>() {
            @Override
            public void onChanged(@Nullable List<NewsEntity> newsEntities) {
                swipeRefreshLayout.setRefreshing(false);
                newsListAdapter.clearItems();
                newsListAdapter.addItems(newsEntities);
                newsListAdapter.notifyDataSetChanged();
                newsEntitiesList = newsEntities;
            }
        });
    }

    private void setUI() {
        recyclerView = (RecyclerView) findViewById(R.id.news_list);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        newsViewModel = new NewsViewModel(this);

        newsListAdapter = new NewsListAdapter(MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsListAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        newsListAdapter.setClickListener(new NewsListAdapter.ClickListeners() {
            @Override
            public void onRowClick(int position, View v) {
                NewsEntity newsEntity = newsEntitiesList.get(position);
                if (newsEntity != null)
                    startActivity(new Intent(MainActivity.this, NewsDetails.class).putExtra("news", newsEntity));

            }
        });
    }
}

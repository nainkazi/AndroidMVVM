package com.archetecture.androidmvvm.ui.news_details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.archetecture.androidmvvm.R;
import com.archetecture.androidmvvm.data.models.NewsEntity;
import com.archetecture.androidmvvm.utils.ImageUtils;
import com.google.android.material.snackbar.Snackbar;

/**
 * Created by Nain Kazi on 5/26/2019.
 */

public class NewsDetails extends AppCompatActivity {

    TextView tv_title;
    TextView tv_summery;
    Button bt_web;
    ImageView imageView;
    NewsEntity newsEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        newsEntity = getIntent().getParcelableExtra("news");
        setUI();
    }

    private void setUI() {
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_summery = (TextView) findViewById(R.id.summary_content);
        bt_web = (Button) findViewById(R.id.full_story_link);
        tv_summery = (TextView) findViewById(R.id.summary_content);
        setData();
        bt_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showWebActivity();
            }
        });
    }

    private void setData() {
        tv_title.setText(newsEntity.getTitle());
        tv_summery.setText(newsEntity.getSummary());
        if (newsEntity.getMediaEntityList() != null && !newsEntity.getMediaEntityList().isEmpty()) {
            String thumbnailURL = newsEntity.getMediaEntityList().get(0).getUrl();
            ImageUtils.loadImage(this, thumbnailURL, imageView);
        }
    }

    private void showWebActivity() {
        if (newsEntity != null && !TextUtils.isEmpty(newsEntity.getArticleUrl())) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(newsEntity.getArticleUrl()));
            startActivity(intent);
        } else {
            Snackbar.make(bt_web.getRootView(), getString(R.string.not_found), Snackbar.LENGTH_LONG).show();
        }
    }
}

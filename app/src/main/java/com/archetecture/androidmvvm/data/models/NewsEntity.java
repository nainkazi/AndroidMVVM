package com.archetecture.androidmvvm.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * This represents a news item
 */
public class NewsEntity implements Parcelable {

    private static final String TAG = NewsEntity.class.getSimpleName();
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("abstract")
    @Expose
    private String summary;
    @SerializedName("url")
    @Expose
    private String articleUrl;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("multimedia")
    @Expose
    private ArrayList<MediaEntity> mediaEntityList;


    public NewsEntity(Parcel in) {
        title = in.readString();
        summary = in.readString();
        articleUrl = in.readString();
        byline = in.readString();
        publishedDate = in.readString();
        mediaEntityList = in.createTypedArrayList(MediaEntity.CREATOR);
    }

    public NewsEntity() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(summary);
        dest.writeString(articleUrl);
        dest.writeString(byline);
        dest.writeString(publishedDate);
        dest.writeTypedList(mediaEntityList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        @Override
        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    public static String getTAG() {
        return TAG;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ArrayList<MediaEntity> getMediaEntityList() {
        return mediaEntityList;
    }

    public void setMediaEntityList(ArrayList<MediaEntity> mediaEntityList) {
        this.mediaEntityList = mediaEntityList;
    }
}

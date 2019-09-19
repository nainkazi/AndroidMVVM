package com.archetecture.androidmvvm.ui.news_feeds;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.archetecture.androidmvvm.R;
import com.archetecture.androidmvvm.data.models.NewsEntity;
import com.archetecture.androidmvvm.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;


public class NewsListAdapter extends RecyclerView.Adapter {

    private List<NewsEntity> dataSet;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private Context context;
    private boolean isLoading;

    //constructor...
    public NewsListAdapter(Context context) {
        this.context = context;
        this.dataSet = new ArrayList<>();
    }

    public void setLoaded() {
        isLoading = false;
    }

    public void addItems(List<NewsEntity> items) {
        dataSet.addAll(items);
        notifyDataSetChanged();
    }

    public List<NewsEntity> getItems() {
        return dataSet;
    }

    public void clearItems() {
        dataSet.clear();
        notifyDataSetChanged();
    }

    public void removeLoadingItem() {
        dataSet.remove(dataSet.size() - 1);
        notifyItemRemoved(dataSet.size());
    }

    public void addLoadingItem() {
        dataSet.add(null);
        notifyItemInserted(dataSet.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return dataSet.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
                return new NewsListAdapter.ItemTypeViewHolder(view);
            case VIEW_TYPE_LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_item, parent, false);
                return new NewsListAdapter.LoadingViewHolder(view);

        }
        return null;


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {
        NewsEntity newsEntity = dataSet.get(listPosition);
        if (holder instanceof ItemTypeViewHolder) {
            try {
                String thumbnailURL = "";
                ItemTypeViewHolder itemTypeViewHolder = (ItemTypeViewHolder) holder;
                itemTypeViewHolder.newsTitle.setText(newsEntity.getTitle());
                if (newsEntity.getMediaEntityList() != null && !newsEntity.getMediaEntityList().isEmpty()) {
                    thumbnailURL = newsEntity.getMediaEntityList().get(0).getUrl();
                    ImageUtils.loadImage(context,thumbnailURL, itemTypeViewHolder.imageView);
                }
            } catch (Exception e) {

            }
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    /*Listeners*/
    NewsListAdapter.ClickListeners clickListener;

    public void setClickListener(NewsListAdapter.ClickListeners clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListeners {
        void onRowClick(int position, View v);

    }

    class ItemTypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView newsTitle;
        ImageView imageView;
        View mParentFrame;

        public ItemTypeViewHolder(View itemView) {
            super(itemView);
            this.mParentFrame = itemView.findViewById(R.id.rl_header);
            this.newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            this.imageView = (ImageView) itemView.findViewById(R.id.news_item_image);

            this.mParentFrame.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_header:
                    if (clickListener != null)
                        clickListener.onRowClick(getAdapterPosition(), v);
                    break;
            }
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}

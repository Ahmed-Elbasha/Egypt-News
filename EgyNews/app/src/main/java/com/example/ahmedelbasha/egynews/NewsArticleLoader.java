package com.example.ahmedelbasha.egynews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class NewsArticleLoader extends AsyncTaskLoader<List<NewsArticle>> {

    private String mURl;

    public NewsArticleLoader(Context context, String url) {
        super(context);
        mURl = url;
    }

    /**
     * Subclasses must implement this to take care of loading their data,
     * as per {@link #startLoading()}.  This is not called by clients directly,
     * but as a result of a call to {@link #startLoading()}.
     */
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {

        if (mURl == null) {
            return null;
        }

        List<NewsArticle> newsArticles = QueryUtils.fetchNewsArticlesData(mURl);
        return newsArticles;
    }
}

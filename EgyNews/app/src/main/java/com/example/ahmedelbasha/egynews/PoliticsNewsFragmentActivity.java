package com.example.ahmedelbasha.egynews;


import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class PoliticsNewsFragmentActivity extends FragmentActivity {

    private static final String POLITICS_NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=eg&category=politics&apiKey=fee9c999a5064459a9f4954ab9b7a020";
    private static final String LOG_TAG = PoliticsNewsFragmentActivity.class.getName();
    private static final int NEWS_ARTICLE_LOADER_ID = 1;
    private static LoaderManager loaderManager;
    private static NewsArticleAdapter newsArticleAdapter;
    private  TextView issueStateTextView;
    private ProgressBar loadingIndicator;

    /**
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.refresh_action:
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PoliticsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsArticle>>
    {


        public PoliticsFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_layout, container, false);
        }

        @Override
        public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
            return null;
        }

        @Override
        public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> data) {

        }

        @Override
        public void onLoaderReset(Loader<List<NewsArticle>> loader) {

        }
    }
}




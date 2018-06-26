package com.example.ahmedelbasha.egynews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BusinessNewsFragmentActivity extends FragmentActivity {

    public static final String BUSINESS_NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=eg&category=business&apiKey=fee9c999a5064459a9f4954ab9b7a020";
    public static final String LOG_TAG = BusinessNewsFragmentActivity.class.getName();
    public static final int NEWS_ARTICLE_LOADER_ID = 1;
    public static LoaderManager loaderManager;
    public static NewsArticleAdapter newsArticleAdapter;
    public static  TextView issueStateTextView;
    public static ProgressBar loadingIndicator;

    /**
     * Perform initialization of all fragments.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
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

    public static class BusinessFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {

        public BusinessFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

            issueStateTextView = rootView.findViewById(R.id.issues_text_view);

            GridView gridView = rootView.findViewById(R.id.grid_list);

            newsArticleAdapter = new NewsArticleAdapter(getActivity(), new ArrayList<NewsArticle>());

            gridView.setAdapter(newsArticleAdapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsArticle currentNewsArticle = newsArticleAdapter.getItem(position);

                    String newsArticleUrl = null;
                    if (currentNewsArticle != null) {
                        newsArticleUrl = currentNewsArticle.getArticleURL();
                    }
                    moveTozNewsArticleUrl(newsArticleUrl);
                }
            });

            loadingIndicator = rootView.findViewById(R.id.loading_indicator);

            loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader(NEWS_ARTICLE_LOADER_ID, null, this);

            return rootView;
        }

        private void moveTozNewsArticleUrl(String newsArticleURL) {
            Uri newsArticleUri = Uri.parse(newsArticleURL);
            Intent moveTozNewsArticleUrlIntent = new Intent( Intent.ACTION_VIEW, newsArticleUri);
            if (moveTozNewsArticleUrlIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(moveTozNewsArticleUrlIntent);
            }
        }

        @Override
        public Loader<List<NewsArticle>> onCreateLoader(int id, Bundle args) {
            return new NewsArticleLoader(getActivity(), BUSINESS_NEWS_REQUEST_URL);
        }

        @Override
        public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> newsArticles) {
            newsArticleAdapter.clear();

            if (newsArticles!= null && !newsArticles.isEmpty()) {
                newsArticles.addAll(newsArticles);
            }
        }

        @Override
        public void onLoaderReset(Loader<List<NewsArticle>> loader) {
            newsArticleAdapter.clear();
        }
    }
}



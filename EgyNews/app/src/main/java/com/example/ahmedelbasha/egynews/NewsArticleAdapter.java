package com.example.ahmedelbasha.egynews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsArticleAdapter extends ArrayAdapter<NewsArticle>{

    public NewsArticleAdapter(@NonNull Context context, @NonNull List<NewsArticle> newsArticles) {
        super(context, 0, newsArticles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        NewsArticle currentNewsArticle = getItem(position);

        TextView newsArticleTitleTextView = listItem.findViewById(R.id.news_article_title);
        newsArticleTitleTextView.setText(currentNewsArticle.getArticleTitle());

        TextView newsArticleSourceTextView = listItem.findViewById(R.id.news_article_source);
        newsArticleSourceTextView.setText(currentNewsArticle.getArticleSourceName());

        TextView newsArticlePublishDateTextView = listItem.findViewById(R.id.news_article_publish_date);
        newsArticlePublishDateTextView.setText(currentNewsArticle.getPublishDate());

        ImageView newsImageImageView = listItem.findViewById(R.id.news_article_image);

        String newsImageUrl = currentNewsArticle.getArticleImageUrl();

        Picasso.with(getContext()).
                load(newsImageUrl).
                centerCrop().
                fit().
                error(R.drawable.no_image).
                placeholder(R.drawable.no_image).
                into(newsImageImageView);


        return listItem;
    }
}

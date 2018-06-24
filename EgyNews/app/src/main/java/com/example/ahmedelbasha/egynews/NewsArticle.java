package com.example.ahmedelbasha.egynews;

public class NewsArticle {

    private String articleSourceName;
    private String articleTitle;
    private String articleURL;
    private String articleImageUrl;
    private String publishDate;

    public NewsArticle(String articleSourceName, String articleTitle, String articleURL, String articleImageUrl, String publishDate) {
        this.articleSourceName = articleSourceName;
        this.articleTitle = articleTitle;
        this.articleURL = articleURL;
        this.articleImageUrl = articleImageUrl;
        this.publishDate = publishDate;
    }

    public String getArticleSourceName() {
        return articleSourceName;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getArticleURL() {
        return articleURL;
    }

    public String getArticleImageUrl() {
        return articleImageUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }
}

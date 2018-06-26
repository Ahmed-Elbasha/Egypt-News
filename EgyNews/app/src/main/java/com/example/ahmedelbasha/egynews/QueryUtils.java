package com.example.ahmedelbasha.egynews;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getName();

    private QueryUtils() {
    }

    public static List<NewsArticle> fetchNewsArticlesData(String rquestUrl) {

        URL url = createUrl(rquestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<NewsArticle> newsArticles = extractArticlesFromJsonData(jsonResponse);

        return newsArticles;
    }

    private static String makeHttpRequest(URL url) throws IOException {

        String jsonResponse = "";

        if (url == null) {
            return  jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(400000);
            urlConnection.setConnectTimeout(400000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e ) {
            Log.e(LOG_TAG, "Problem retrieving the newsArticle JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringOutput = new StringBuilder();

        if (inputStream  != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            if (line != null) {
                stringOutput.append(line);
                line = reader.readLine();
            }
        }

        return stringOutput.toString();
    }

    private static URL createUrl(String rquestUrl) {
        URL url = null;

        try {
            url = new URL(rquestUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }

        return url;
    }

    private static List<NewsArticle> extractArticlesFromJsonData(String jsonResponse) {
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        List<NewsArticle> newsArticles = new ArrayList<>();

        try {
            JSONObject rootJsonObject = new JSONObject(jsonResponse);

            JSONArray articles = rootJsonObject.optJSONArray("articles");

            for (int i = 0; i < articles.length(); i++) {
                JSONObject article = articles.optJSONObject(i);

                JSONObject source = article.getJSONObject("source");
                String sourceName = source.optString("name");

                String title = article.optString("title");
                String newsArticleUrl = article.optString("url");
                String articleImageUrl = article.optString("urlToImage");
                String publishDate = article.optString("publishedAt");

                newsArticles.add(new NewsArticle(sourceName, title, newsArticleUrl, articleImageUrl, publishDate));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the newsArticle JSON results", e);
        }

        return newsArticles;
    }
}

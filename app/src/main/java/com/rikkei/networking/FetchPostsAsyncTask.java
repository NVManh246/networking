package com.rikkei.networking;

import android.os.AsyncTask;
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
import java.util.ArrayList;
import java.util.List;

public class FetchPostsAsyncTask extends AsyncTask<String, Void, List<Post>> {

    private static final String GET_METHOD = "GET";
    private OnCompleteListener mListener;

    public FetchPostsAsyncTask(OnCompleteListener listener) {
        mListener = listener;
    }

    @Override
    protected List<Post> doInBackground(String... strings) {
        List<Post> posts = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET_METHOD);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String data = convertToString(inputStream);
            posts.addAll(convertToObject(data));
        } catch (MalformedURLException e) {
            mListener.onError(e);
        } catch (IOException e) {
            mListener.onError(e);
        } catch (JSONException e) {
            mListener.onError(e);
        }
        return posts;
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        super.onPostExecute(posts);
        mListener.onSuccess(posts);
    }

    private String convertToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder data = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            data.append(line);
        }
        return data.toString();
    }

    private List<Post> convertToObject(String data) throws JSONException {
        List<Post> posts = new ArrayList<>();
        JSONArray jsonArrayPost = new JSONArray(data);
        for(int i = 0; i < jsonArrayPost.length(); i++) {
            JSONObject jsonObjectPost = jsonArrayPost.getJSONObject(i);
            int userId = jsonObjectPost.getInt(Post.USER_ID);
            int id = jsonObjectPost.getInt(Post.ID);
            String title = jsonObjectPost.getString(Post.TITLE);
            String body = jsonObjectPost.getString(Post.BODY);
            Post post = new Post(id, userId, title, body);
            posts.add(post);
        }
        return posts;
    }
}

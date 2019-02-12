package com.rikkei.networking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements OnCompleteListener, PostAdapter.OnItemClickListener {

    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String TAG = "tag";

    private FetchPostsAsyncTask mFetchPostsAsyncTask;
    private List<Post> mPosts;
    private PostAdapter mPostAdapter;
    private RecyclerView mRecyclerPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerPost = findViewById(R.id.recycler_post);
        mPosts = new ArrayList<>();
        mPostAdapter = new PostAdapter(this, mPosts, this);
        mRecyclerPost.setAdapter(mPostAdapter);
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(this));

        mFetchPostsAsyncTask = new FetchPostsAsyncTask(this);
        mFetchPostsAsyncTask.execute(POST_URL);
    }

    @Override
    public void onSuccess(List<Post> posts) {
        mPostAdapter.insertPosts(posts);
    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG, e.toString());
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = PostDetailActivity.getPostDetailIntent(this, mPosts.get(position));
        startActivity(intent);
    }
}

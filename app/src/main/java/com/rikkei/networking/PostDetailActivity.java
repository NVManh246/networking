package com.rikkei.networking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PostDetailActivity extends AppCompatActivity {

    private static final String EXTRA_POST = "post";

    private TextView mTextPostBody;

    public static Intent getPostDetailIntent(Context context, Post post) {
        Intent intent = new Intent(context, PostDetailActivity.class);
        intent.putExtra(EXTRA_POST, post);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        mTextPostBody = findViewById(R.id.text_post_body);
        Post post = getIntent().getParcelableExtra(EXTRA_POST);
        mTextPostBody.setText(post.getBody());
    }
}

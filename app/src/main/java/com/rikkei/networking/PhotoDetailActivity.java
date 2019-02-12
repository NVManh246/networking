package com.rikkei.networking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "photo";

    private ImageView mImagePhoto;

    public static Intent getPhotoDetailIntent(Context context, Photo photo) {
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra(EXTRA_PHOTO, photo);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_detail_activity);
        mImagePhoto = findViewById(R.id.image_photo);
        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);
        Glide.with(this)
                .load(photo.getUrl())
                .into(mImagePhoto);
    }
}

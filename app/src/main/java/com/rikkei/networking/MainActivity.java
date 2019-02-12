package com.rikkei.networking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PhotoAdapter.OnItemClickListener {

    private static final String TAG = "tag";

    private RecyclerView mRecyclerPhoto;
    private List<Photo> mPhotos;
    private PhotoAdapter mPhotoAdapter;

    private PhotoApi mPhotoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerPhoto = findViewById(R.id.recycler_photo);
        mPhotos = new ArrayList<>();
        mPhotoAdapter = new PhotoAdapter(this, mPhotos, this);
        mRecyclerPhoto.setAdapter(mPhotoAdapter);
        mRecyclerPhoto.setLayoutManager(new LinearLayoutManager(this));
        mPhotoApi = ApiFactory.getApi();
        fetchPhots();
    }

    private void fetchPhots() {
        Call<List<Photo>> call = mPhotoApi.fetchPhotos();
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                mPhotoAdapter.insertPhotos(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(TAG, t.toString());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = PhotoDetailActivity
                .getPhotoDetailIntent(this, mPhotos.get(position));
        startActivity(intent);
    }
}

package com.rikkei.networking;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoApi {
    @GET("photos")
    Call<List<Photo>> fetchPhotos();
}

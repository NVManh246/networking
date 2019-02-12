package com.rikkei.networking;

public class ApiFactory {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static PhotoApi getApi() {
        return ApiConfig.getRetrofit(BASE_URL).create(PhotoApi.class);
    }
}

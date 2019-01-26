package com.example.zuzia.cookbook.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    public static Retrofit getRetrofitInstance(){
        Gson gson = new GsonBuilder()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

}

package com.example.zuzia.cookbook.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

    @GET("latest.php")
    Call<Meals> getMeals();
}

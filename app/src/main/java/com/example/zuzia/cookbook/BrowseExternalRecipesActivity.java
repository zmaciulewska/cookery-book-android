package com.example.zuzia.cookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.zuzia.cookbook.retrofit.Meal;
import com.example.zuzia.cookbook.retrofit.MealListAdapter;
import com.example.zuzia.cookbook.retrofit.Meals;
import com.example.zuzia.cookbook.retrofit.RestClient;
import com.example.zuzia.cookbook.retrofit.RestService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BrowseExternalRecipesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MealListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_external_recipes);

        RestService service = RestClient.getRetrofitInstance().create(RestService.class);
        Call<Meals> call = service.getMeals();
        call.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                renderList(response.body());
                Toast.makeText(BrowseExternalRecipesActivity.this, "Connection with rest service succeded.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Toast.makeText(BrowseExternalRecipesActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void renderList(Meals body) {
        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MealListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        adapter.setList(body);
    }
}

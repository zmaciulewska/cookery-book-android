package com.example.zuzia.cookbook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;
import com.example.zuzia.cookbook.recyclerView.RecipeListAdapter;

import java.util.List;

public class FindByTitleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Recipe> recipes;
    private String title;
    private RecipeViewModel recipeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_by_title);
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        Intent intent = getIntent();
        title = intent.getStringExtra("Q");

        recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipeListAdapter();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        recipeViewModel.findByTitle("%" + title + "%").observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> tasks) {
                adapter.setRecipeList(tasks);
            }
        });
    }
}

package com.example.zuzia.cookbook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;
import com.example.zuzia.cookbook.recyclerView.RecipeListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecipeListAdapter adapter;

    public static RecipeViewModel recipeViewModel;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CookBook", "On create started");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        adapter = new RecipeListAdapter(context);
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipesTmp) {
                Log.d("CookBook", "Recipes change detected");
                adapter.setRecipeList(recipesTmp);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateRecipeActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_find) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View formElementsView = inflater.inflate(R.layout.find_recipe, null, false);
            final EditText recipeTitle = formElementsView.findViewById(R.id.findTitle);
            new AlertDialog.Builder(context)
                    .setView(formElementsView)
                    .setTitle("Find recipe")
                    .setPositiveButton("Find",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    String title = recipeTitle.getText().toString();
                                    Intent intent = new Intent(context, FindByTitleActivity.class);
                                    intent.putExtra("Q", title);
                                    startActivity(intent);
                                    dialog.cancel();
                                }
                            }).show();
        }

        if (id == R.id.action_inspire) {
            Log.d("CookBook", "Wybrano inspire");
            Intent intent = new Intent(context, BrowseExternalRecipesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}

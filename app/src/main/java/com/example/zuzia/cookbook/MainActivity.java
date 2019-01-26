package com.example.zuzia.cookbook;

import android.arch.lifecycle.Observer;
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
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.EditText;

import com.example.zuzia.cookbook.database.RecipeViewModel;
//import com.example.zuzia.cookbook.recipesManager.Recipe;
import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.recipesManager.RecipesManager;
import com.example.zuzia.cookbook.recyclerView.ItemClickSupport;
import com.example.zuzia.cookbook.recyclerView.RecipeListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

   // private RecipesManager recipesManager = RecipesManager.getInstance();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    //private RecyclerView.Adapter adapter;
    private RecipeListAdapter adapter;
    private List<Recipe> recipes;
    public static RecipeViewModel recipeViewModel;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("CookBook", "On create started" );
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //List<Recipe> recipeList = recipesManager.getRecipeList();
        context = this;
        Log.d("CookBook", "Context initialize" );
        adapter = new RecipeListAdapter();
        Log.d("CookBook", "New Adapter" );
        recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        Log.d("CookBook", "Recipe view model created" );
        recipes = recipeViewModel.getAllRecipes().getValue();
        Log.d("CookBook", "All recipes get "  );





        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerViewf
        recyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        //adapter = new RecipeListAdapter(recipeList);
        recyclerView.setAdapter(adapter);

        recipeViewModel.getAllRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                Log.d("CookBook", "Recipes change detected" );
                adapter.setRecipeList(recipes);
                Log.d("CookBook", "Recipe list set into adapter" );
            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("CookBook", "onItemClick position: " + position);
                Intent intent = new Intent(MainActivity.this, RecipeDetailsActivity.class);
                intent.putExtra("recipeId", position);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
             /*   startActivity(new Intent(MainActivity.this, SensorsActivity.class));*/
                startActivity(new Intent(MainActivity.this, CreateRecipeActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_find) {
            Log.d("CookBook","Wybrano settings");
           // startActivity(new Intent(MainActivity.this, FindByTitleActivity.class));

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
            Log.d("CookBook","Wybrano inspire");
            Intent intent = new Intent(context, BrowseExternalRecipesActivity.class);
            //intent.putExtra("Q", title);
            startActivity(intent);


            // startActivity(new Intent(MainActivity.this, FindByTitleActivity.class));

            /*LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                            }).show();*/
        }
        return super.onOptionsItemSelected(item);
    }
}

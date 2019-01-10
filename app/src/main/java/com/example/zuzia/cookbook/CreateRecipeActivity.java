package com.example.zuzia.cookbook;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;
//import com.example.zuzia.cookbook.recipesManager.Recipe;
import com.example.zuzia.cookbook.recipesManager.RecipesManager;

import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity {

    //private RecipesManager recipesManager = RecipesManager.getInstance();

    private MainActivity mainActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Logs", "CreatingTaskActivity: onCreate method.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
    }

    public void confirmButtonClicked(View view) {
        Log.d("Logs", "CreatingTaskActivity: confirmButtonClicked method.");
        String title = ((EditText) findViewById(R.id.editText_title)).getText().toString();
        String description = ((EditText) findViewById(R.id.editText_description)).getText().toString();
        String instruction = ((EditText) findViewById(R.id.editText_instruction)).getText().toString();
        Recipe recipe  = new Recipe();
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setInstruction(instruction);
        List<String> ingredients = new ArrayList<>();
        ingredients.add("mleko");
        ingredients.add("masło");
        //recipe.setIngredients(ingredients);
        //recipe.setImageId(R.drawable.culinary);
        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.insert(recipe);
       // recipesManager.getRecipeList().add(recipe);
        Snackbar.make(view, "Creating task, please wait", Snackbar.LENGTH_LONG)
                .show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CreateRecipeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }

}

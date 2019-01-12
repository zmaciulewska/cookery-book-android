package com.example.zuzia.cookbook.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeViewModel(Application application) {
        super(application);

        Log.d("CookBook", "Initialize view model" );
        recipeRepository = new RecipeRepository(application);
        allRecipes = recipeRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        Log.d("CookBook", "Get all recipes invoked. Recipes: " + allRecipes.toString() );
        return allRecipes; }

    public void insert(Recipe recipe) { recipeRepository.insert(recipe); }

    public void update(Recipe recipe){
        recipeRepository.update(recipe);
    }

    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }

    public LiveData<List<Recipe>> findByTitle(String title) {
       return recipeRepository.findByTitle(title);
    }
}

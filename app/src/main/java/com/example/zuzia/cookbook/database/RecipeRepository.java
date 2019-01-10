package com.example.zuzia.cookbook.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RecipeRepository {

    private RecipeDao recipeDao;
    private LiveData<List<Recipe>> allRecipes;

    public RecipeRepository(Application application) {
        RecipeDatabase db = RecipeDatabase.getRecipeDatabase(application);
        recipeDao = db.recipeDao();
        allRecipes = recipeDao.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes(){
        return allRecipes;
    }

    public Recipe getSingleRecord(int id) {
        return recipeDao.getSingleRecord(id);
    }

    public void insert(Recipe recipe) {
        recipeDao.insert(recipe);
    }

    public void update(Recipe recipe) {
        recipeDao.update(recipe);
    }

    public void delete(Recipe recipe) {
        recipeDao.delete(recipe);
    }

    public LiveData<List<Recipe>> findByTitle(String title) {
        return recipeDao.findByTitle(title);
    }

    private static class insertAsyncTask extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao asyncRecipeDao;

        insertAsyncTask(RecipeDao dao) {
            asyncRecipeDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... params) {
            asyncRecipeDao.insert(params[0]);
            return null;
        }
    }

}

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

    public LiveData<Recipe> getSingleRecord(int id) {
        return recipeDao.getSingleRecord(id);
    }

    public void insert(Recipe recipe) {
        new insertAsyncTask(recipeDao).execute(recipe);    }

    public void update(Recipe recipe) {
        new updateAsyncRecipe(recipeDao).execute(recipe);
    }

    public void delete(Recipe recipe) {
        new deleteAsyncRecipe(recipeDao).execute(recipe);
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

    private static class updateAsyncRecipe extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao asyncRecipeDao;

        updateAsyncRecipe(RecipeDao dao) {
            asyncRecipeDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... params) {
            asyncRecipeDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncRecipe extends AsyncTask<Recipe, Void, Void> {

        private RecipeDao asyncRecipeDao;

        deleteAsyncRecipe(RecipeDao dao) {
            asyncRecipeDao = dao;
        }

        @Override
        protected Void doInBackground(final Recipe... params) {
            asyncRecipeDao.delete(params[0]);
            return null;
        }
    }

    private static class readSingleRecordAsyncTask extends AsyncTask<Integer, Void, Void> {

        private RecipeDao asyncRecipeDao;

        readSingleRecordAsyncTask(RecipeDao dao) {
            asyncRecipeDao = dao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            asyncRecipeDao.getSingleRecord(integers[0]);
            return null;
        }
    }

}

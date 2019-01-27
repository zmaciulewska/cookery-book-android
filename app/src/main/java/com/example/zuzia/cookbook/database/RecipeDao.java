package com.example.zuzia.cookbook.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insert(Recipe recipe);

    @Update
    void update(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Query("select * from " + RecipeDatabase.TABLE_NAME)
    LiveData<List<Recipe>> getAllRecipes();

    @Query("Select * FROM " + RecipeDatabase.TABLE_NAME + " where recipe_id = :id")
    LiveData<Recipe> getSingleRecord(int id);

    @Query("Select * from " + RecipeDatabase.TABLE_NAME + " where title like :title")
    LiveData<List<Recipe>> findByTitle(String title);


}

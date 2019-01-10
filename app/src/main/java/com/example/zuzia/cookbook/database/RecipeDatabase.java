package com.example.zuzia.cookbook.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import static com.example.zuzia.cookbook.database.RecipeDatabase.DATABASE_VERSION;

@Database(entities = {Recipe.class}, version = DATABASE_VERSION)
public abstract class RecipeDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recipe_database.db";
    public static final String TABLE_NAME = "Recipe";

    public abstract RecipeDao recipeDao();

    private static volatile RecipeDatabase INSTANCE;

    static RecipeDatabase getRecipeDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, DATABASE_NAME)
                            .addCallback(callback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback callback = new Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }

    };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {

        private final RecipeDao recipeDao;

        public PopulateDBAsync(RecipeDatabase db) {
            recipeDao = db.recipeDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}

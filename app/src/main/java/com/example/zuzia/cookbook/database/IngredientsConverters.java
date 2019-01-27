package com.example.zuzia.cookbook.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class IngredientsConverters {
    static Gson gson = new Gson();

    @TypeConverter
    public static List<String> stringToIngredients(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String fromIngredientsToString(List<String> ingredients) {
        return gson.toJson(ingredients);
    }
}

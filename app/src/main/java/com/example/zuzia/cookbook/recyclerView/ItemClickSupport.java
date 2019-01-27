package com.example.zuzia.cookbook.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zuzia.cookbook.MainActivity;
import com.example.zuzia.cookbook.R;
import com.example.zuzia.cookbook.RecipeDetailsActivity;
import com.example.zuzia.cookbook.database.Recipe;

public class ItemClickSupport implements View.OnClickListener {
    private Recipe recipe;
    private Context context;
    public ItemClickSupport(Recipe actualRecipe) {
        recipe = actualRecipe;
    }

    @Override
    public void onClick(View v) {
        context = v.getContext();
        Intent intent = new Intent(context, RecipeDetailsActivity.class);
        intent.putExtra("recipeId", recipe.getId());
        context.startActivity(intent);

    }

}


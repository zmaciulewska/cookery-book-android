package com.example.zuzia.cookbook;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuzia.cookbook.database.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    Button imageButton;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CookBook", "Details on create");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Integer recipeId = getIntent().getExtras().getInt("recipeId", 0);

        LiveData<Recipe> recipeLiveData = MainActivity.recipeViewModel.getRecipe(recipeId);
        recipeLiveData.observe(this, new Observer<Recipe>() {
            @Override
            public void onChanged(@Nullable Recipe recipeTmp) {
                recipe = recipeTmp;
                display();
            }
        });


    }

    private void display() {
        // ingredients list
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_view_element, recipe.getIngredients());
        ListView ingredientsList = findViewById(R.id.ingredients_list);
        ingredientsList.setAdapter(adapter);

        int ingredientsAmount;
        if(recipe.getIngredients() != null) ingredientsAmount = recipe.getIngredients().size();
        else ingredientsAmount = 0;
        String result = getResources().getQuantityString(R.plurals.details_ingredients, ingredientsAmount, ingredientsAmount);
        TextView ingredientsSubtitle = findViewById(R.id.ingredients_subtitle);
        ingredientsSubtitle.setText(result);


        // title
        String title = recipe.getTitle();
        TextView titleTextView = findViewById(R.id.details_title);
        titleTextView.setText(title);
        // instruction
        String instruction = recipe.getInstruction();
        TextView instructionTextView = findViewById(R.id.details_instruction);
        instructionTextView.setText(instruction);
        // description
        String description = recipe.getDescription();
        TextView descriptionTextView = findViewById(R.id.details_description);
        descriptionTextView.setText(description);
        // photo
        //int photoId = recipe.getImageId();
        byte[] array = recipe.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(array, 0, array.length);
        ImageView imageView = findViewById(R.id.details_photo);
        imageView.setImageBitmap(bitmap);

    }

}

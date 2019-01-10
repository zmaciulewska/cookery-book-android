package com.example.zuzia.cookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zuzia.cookbook.recipesManager.Recipe;
import com.example.zuzia.cookbook.recipesManager.RecipesManager;

public class RecipeDetailsActivity extends AppCompatActivity {

    private RecipesManager recipesManager = RecipesManager.getInstance();
    Button imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Integer recipeId = getIntent().getExtras().getInt("recipeId", 0);
        Recipe recipe = recipesManager.getRecipeList().get(recipeId);

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
        int photoId = recipe.getImageId();
        ImageView imageView = findViewById(R.id.details_photo);
        imageView.setImageResource(photoId);


        addListenerOnButton();
    }
    public void addListenerOnButton() {

        imageButton = (Button) findViewById(R.id.imageButtonSelector);

        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Toast.makeText(RecipeDetailsActivity.this,
                        "ImageButton (selector) is clicked!",
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

}
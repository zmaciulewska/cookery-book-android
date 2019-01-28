package com.example.zuzia.cookbook;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateRecipeActivity extends AppCompatActivity {

    private List<String> ingredients;
    private ArrayAdapter adapter;
    private ListView ingredientsList;
    private byte[] byteImage;
    private Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Logs", "CreatingTaskActivity: onCreate method.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        // ingredients list
        ingredientsList = findViewById(R.id.ingredients_mutable_list);
        ingredients = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.list_view_element, ingredients);
        ingredientsList.setAdapter(adapter);
        ingredientsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                Log.v("CookBook", " long clicked pos: " + pos);
                ingredients.remove(pos);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    public void ingredientButtonClicked(View view) {
        String ingredient = ((EditText) findViewById(R.id.editText_ingredient)).getText().toString();
        ingredients.add(ingredient);
        adapter.notifyDataSetChanged();
        ((EditText) findViewById(R.id.editText_ingredient)).setText("");

    }


    public void confirmButtonClicked(View view) {
        Log.d("Logs", "CreatingTaskActivity: confirmButtonClicked method.");
        String title = ((EditText) findViewById(R.id.editText_title)).getText().toString();
        String description = ((EditText) findViewById(R.id.editText_description)).getText().toString();
        String instruction = ((EditText) findViewById(R.id.editText_instruction)).getText().toString();
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setDescription(description);
        recipe.setInstruction(instruction);

        recipe.setIngredients(ingredients);
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byteImage = stream.toByteArray();
            recipe.setImage(byteImage);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.culinary);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Bitmap bitmapDrawable = ((BitmapDrawable) drawable).getBitmap();
            bitmapDrawable.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bitMapData = stream.toByteArray();
            recipe.setImage(bitMapData);
        }


        RecipeViewModel recipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        recipeViewModel.insert(recipe);
        // recipesManager.getRecipeList().add(recipe);
        Snackbar.make(view, "Creating recipe, please wait", Snackbar.LENGTH_LONG)
                .show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CreateRecipeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);

    }

    public static final int GET_FROM_GALLERY = 3;


    public void onGalleryButtonAction(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

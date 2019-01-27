package com.example.zuzia.cookbook.recyclerView;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zuzia.cookbook.MainActivity;
import com.example.zuzia.cookbook.R;
import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;

import java.util.List;

class LongClickListenerRecipeRecord implements View.OnLongClickListener {
    
    private Recipe actualRecipe;
    private Context context;
    private List<String> ingredients;
    private ArrayAdapter adapter;
    
    public LongClickListenerRecipeRecord(Recipe actualRecipe) {
        this.actualRecipe = actualRecipe;
    }

    @Override
    public boolean onLongClick(View v) {
        context = v.getContext();
        final CharSequence[] items = {"EDIT", "DELETE"};
        new AlertDialog.Builder(context).setTitle("Recipe")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(actualRecipe);
                        } else if (item == 1) {
                            deleteRecord(actualRecipe);
                        }
                        dialog.dismiss();
                    }
                }).show();
        return true;
    }

    private void deleteRecord(Recipe recipe) {
        MainActivity.recipeViewModel.delete(recipe);
        Toast.makeText(context, "Recipe deleted.", Toast.LENGTH_SHORT).show();
    }

    private void editRecord(Recipe recipe) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View formElementsView = inflater.inflate(R.layout.update_form, null, false);
        final EditText title = formElementsView.findViewById(R.id.editText_update_title);
        final EditText description = formElementsView.findViewById(R.id.editText_update_description);
        final EditText instruction = formElementsView.findViewById(R.id.editText_update_instruction);
        final EditText ingredient = formElementsView.findViewById(R.id.edit_ingredient);
        final ListView ingredientsList = formElementsView.findViewById(R.id.ingredients_edit_list);
        final Button ingredientButton = formElementsView.findViewById(R.id.edit_button_ingredient);

        ingredients = recipe.getIngredients();
        adapter = new ArrayAdapter<String>(context, R.layout.list_view_element, ingredients);
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

        ingredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredients.add(ingredient.getText().toString());
                adapter.notifyDataSetChanged();
                ingredient.setText("");
            }
        });

        title.setText(recipe.getTitle());
        description.setText(recipe.getDescription());
        instruction.setText(recipe.getInstruction());
        new AlertDialog.Builder(context).setView(formElementsView).setTitle("Edit recipe")
                .setPositiveButton("Save Changes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        actualRecipe.setTitle(title.getText().toString());
                        actualRecipe.setDescription(description.getText().toString());
                        actualRecipe.setInstruction(instruction.getText().toString());
                        actualRecipe.setIngredients(ingredients);
                        MainActivity.recipeViewModel.update(actualRecipe);
                        Toast.makeText(context, "Recipe updated.", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }).show();
    }
}

package com.example.zuzia.cookbook.recyclerView;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zuzia.cookbook.MainActivity;
import com.example.zuzia.cookbook.R;
import com.example.zuzia.cookbook.database.Recipe;
import com.example.zuzia.cookbook.database.RecipeViewModel;

class LongClickListenerRecipeRecord implements View.OnLongClickListener {
    
    private Recipe actualRecipe;
    private Context context;
    
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
                            //new TableControllerTask(context).delete(id);

                        }
                        dialog.dismiss();
                    }
                }).show();
       // return false;
        return true;
    }

    private void deleteRecord(Recipe recipe) {
        MainActivity.recipeViewModel.delete(recipe);
        Toast.makeText(context, "Recipe deleted.", Toast.LENGTH_SHORT).show();
        //((MainActivity) context).readRecords();
    }

    private void editRecord(Recipe recipe) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View formElementsView = inflater.inflate(R.layout.update_form, null, false);
        final EditText title = formElementsView.findViewById(R.id.editText_update_title);
        final EditText description = formElementsView.findViewById(R.id.editText_update_description);
        final EditText instruction = formElementsView.findViewById(R.id.editText_update_instruction);
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


                        MainActivity.recipeViewModel.update(actualRecipe);

                        //tableControllerTask.update(task1);
                        Toast.makeText(context, "Recipe updated.", Toast.LENGTH_SHORT).show();

                        //((MainActivity) context).readRecords();
                        dialog.cancel();
                    }
                }).show();
    }
}

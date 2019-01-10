package com.example.zuzia.cookbook.recyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zuzia.cookbook.R;
import com.example.zuzia.cookbook.database.Recipe;
//import com.example.zuzia.cookbook.recipesManager.Recipe;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    List<Recipe> recipeList;
    protected Context context;

    public RecipeListAdapter() {
    }

    public static class RecipeListViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView recipeTitle;
        TextView recipeDescription;
        ImageView recipePhoto;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.recipe_list_card_view);
            recipeTitle = itemView.findViewById(R.id.recipe_title);
            recipeDescription = itemView.findViewById(R.id.recipe_description);
            recipePhoto = itemView.findViewById(R.id.recipe_photo);
            /*int width = 300;
            int height = 300;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
            recipePhoto.setLayoutParams(parms);*/
        }


    }

    public RecipeListAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public RecipeListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecipeListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element, viewGroup, false);
        RecipeListViewHolder rvh = new RecipeListViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListViewHolder recipeListViewHolder, int i) {
        recipeListViewHolder.recipeTitle.setText(recipeList.get(i).getTitle());
        recipeListViewHolder.recipeDescription.setText(recipeList.get(i).getDescription());
       // recipeListViewHolder.recipePhoto.setImageResource(recipeList.get(i).getImageId());
        recipeListViewHolder.recipePhoto.setImageResource(R.drawable.culinary);
    }

    @Override
    public int getItemCount() {
        if(recipeList!=null)
            return recipeList.size();
        return 0;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }
}

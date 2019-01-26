package com.example.zuzia.cookbook.retrofit;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealListViewHolder> {

    private Meals list;
    private Context context;

    public MealListAdapter(Context context) {
        this.context = context;
    }

    public static class MealListViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView recipeTitle;
        TextView recipeDescription;
        ImageView recipePhoto;

        public MealListViewHolder(View itemView) {
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

    @NonNull
    @Override
    public MealListAdapter.MealListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_element, viewGroup, false);
        MealListAdapter.MealListViewHolder rvh = new MealListAdapter.MealListViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealListViewHolder mealListViewHolder, int i) {
        mealListViewHolder.recipeTitle.setText(list.getMeals().get(i).getTitle());
        mealListViewHolder.recipeDescription.setText(list.getMeals().get(i).getCategory());
        // mealListViewHolder.recipePhoto.setImageResource(recipeList.get(i).getImageId());
        //mealListViewHolder.recipePhoto.setImageResource(R.drawable.culinary);

        Picasso.with(context)
                .load(list.getMeals().get(i).getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(mealListViewHolder.recipePhoto);

        //mealListViewHolder.cardView.setOnLongClickListener(new LongClickListenerRecipeRecord(recipeList.get(i)));
    }


    @Override
    public int getItemCount() {
        if(list!=null)
            return list.getMeals().size();
        return 0;
    }

    public Meals getList() {
        return list;
    }

    public void setList(Meals list) {
        this.list = list;
    }
}

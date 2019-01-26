package com.example.zuzia.cookbook.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meal implements Serializable {
    @SerializedName("idMeal")
    @Expose
    private String id;
    @SerializedName("strMeal")
    @Expose
    private String title;
    @SerializedName("strCategory")
    @Expose
    private String category;
    @SerializedName("strInstructions")
    @Expose
    private String instructions;
    @SerializedName("strMealThumb")
    @Expose
    private String image;

    public Meal(String title, String category, String instructions) {
        this.title = title;
        this.category = category;
        this.instructions = instructions;
    }

    public Meal() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

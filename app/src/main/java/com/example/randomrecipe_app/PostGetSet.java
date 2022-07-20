package com.example.randomrecipe_app;

import java.io.Serializable;

public class PostGetSet implements Serializable {
    private String title;
    private String ingredients;
    private String instructions;
    private String time;
    private String servings;
    private String imageUrl;
    private String author;

    public PostGetSet(String title, String ingredients, String instructions, String time, String servings, String imageUrl, String author) {
        this.title = title;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.time = time;
        this.servings = servings;
        this.imageUrl = imageUrl;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getTime() {
        return time;
    }

    public String getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthor() {
        return author;
    }
}

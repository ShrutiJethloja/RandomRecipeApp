package com.example.randomrecipe_app.Listeners;

import com.example.randomrecipe_app.Models.RandomRecipeApi;

public interface RandomRecipeListener {
    void didFetch(RandomRecipeApi randomRecipeApi, String message);
    void didError(String message);
}

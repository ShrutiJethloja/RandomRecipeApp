package com.example.randomrecipe_app.Listeners;

import com.example.randomrecipe_app.Models.ResultPost;

public interface PostRecipeListener {
    void didSuccess(ResultPost resultPost, String message);
    void didFailure(String message);
}

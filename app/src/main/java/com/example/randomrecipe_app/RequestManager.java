package com.example.randomrecipe_app;

import android.content.Context;
import android.widget.Toast;

import com.example.randomrecipe_app.Listeners.PostRecipeListener;
import com.example.randomrecipe_app.Listeners.RandomRecipeListener;
import com.example.randomrecipe_app.Models.RandomRecipeApi;
import com.example.randomrecipe_app.Models.ResultPost;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipe(RandomRecipeListener listener) {
        CallRandomRecipe callRandomRecipe = retrofit.create(CallRandomRecipe.class);
        Call<RandomRecipeApi> call = callRandomRecipe.callRandomRecipe(context.getString(R.string.api_key), "10");
        call.enqueue(new Callback<RandomRecipeApi>() {
            @Override
            public void onResponse(Call<RandomRecipeApi> call, Response<RandomRecipeApi> response) {
                if(!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApi> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    public void getPostRecipe(PostRecipeListener listener, PostGetSet postGetSet) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title",postGetSet.getTitle())
                .addFormDataPart("ingredients", postGetSet.getIngredients())
                .addFormDataPart("instructions", postGetSet.getInstructions())
                .addFormDataPart("readyInMinutes", postGetSet.getTime())
                .addFormDataPart("servings", postGetSet.getServings())
                .addFormDataPart("mask", "ellipseMask")
                .addFormDataPart("backgroundImage", "https://spoonacular.com/recipeImages/634854-556x370.jpg")
                .addFormDataPart("imageUrl", postGetSet.getImageUrl())
                .addFormDataPart("author", postGetSet.getAuthor())
                .addFormDataPart("backgroundColor", "#ffffff")
                .addFormDataPart("fontColor", "#333333")
                .build();

        CallPostRecipe callPostRecipe = retrofit.create(CallPostRecipe.class);
        callPostRecipe.callPost(context.getString(R.string.api_key), requestBody).enqueue(new Callback<ResultPost>() {
            @Override
            public void onResponse(Call<ResultPost> call, Response<ResultPost> response) {
                if(!response.isSuccessful()) {
                    listener.didFailure(response.message());
                    Toast.makeText(context, "!response.isSuccessful()", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.didSuccess(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<ResultPost> call, Throwable t) {
                Toast.makeText(context, "onFailure", Toast.LENGTH_SHORT).show();
                listener.didFailure(t.getMessage());
            }
        });
    }
}

interface CallRandomRecipe {
    @GET("recipes/random")
    Call<RandomRecipeApi> callRandomRecipe(
            @Query("apiKey") String apiKey,
            @Query("number") String number
    );
}

interface CallPostRecipe {
    @POST("recipes/visualizeRecipe")
    Call<ResultPost> callPost(
            @Query("apiKey") String apiKey,
            @Body RequestBody requestbody
    );
}

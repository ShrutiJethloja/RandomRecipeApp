package com.example.randomrecipe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.randomrecipe_app.Adapters.CustomAdapter;
import com.example.randomrecipe_app.Listeners.RandomRecipeListener;
import com.example.randomrecipe_app.Models.RandomRecipeApi;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    RequestManager manager;
    CustomAdapter adapter;
    RecyclerView recyclerView;
    TextView new_recipe;
    TextView view_recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");

        manager = new RequestManager(this);
        manager.getRandomRecipe(randomRecipeListener);
        dialog.show();

        recyclerView = findViewById(R.id.random_recycler);
        new_recipe = findViewById(R.id.new_recipe);
        new_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        view_recipe = findViewById(R.id.view_recipe);
        view_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PostedImage.class);
                startActivity(intent);
            }
        });

    }

    private final RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
        @Override
        public void didFetch(RandomRecipeApi randomRecipeApi, String message) {
            dialog.dismiss();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
            adapter = new CustomAdapter(MainActivity.this, randomRecipeApi.recipes);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}
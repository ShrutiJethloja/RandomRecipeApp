package com.example.randomrecipe_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.randomrecipe_app.Database.RoomDb;
import com.example.randomrecipe_app.Database.StoreImage;
import com.example.randomrecipe_app.Listeners.PostRecipeListener;
import com.example.randomrecipe_app.Models.ResultPost;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    String sourceUri;
    StoreImage storeImage;
    RequestManager manager;
    EditText post_title, post_ingredients, post_instructions, post_time, post_servings, post_imageUrl, post_author;
    Button post_submit;
    PostGetSet postGetSet;
    RoomDb database;
    List<StoreImage> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        post_title = findViewById(R.id.post_title);
        post_ingredients = findViewById(R.id.post_ingredients);
        post_instructions = findViewById(R.id.post_instructions);
        post_time = findViewById(R.id.post_time);
        post_servings = findViewById(R.id.post_servings);
        post_imageUrl = findViewById(R.id.post_image);
        post_author = findViewById(R.id.post_author);
        post_submit = findViewById(R.id.post_submit);

        post_submit.setOnClickListener(v -> {
            if(post_title.getText().toString().isEmpty() || post_ingredients.getText().toString().isEmpty()|| post_instructions.getText().toString().isEmpty() ||
                    post_time.getText().toString().isEmpty() ||post_servings.getText().toString().isEmpty() || post_imageUrl.getText().toString().isEmpty() ||
                    post_author.getText().toString().isEmpty()) {
                Toast.makeText(PostActivity.this, "Enter all the values!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                postGetSet = new PostGetSet(post_title.getText().toString(), post_ingredients.getText().toString(), post_instructions.getText().toString(),
                        post_time.getText().toString(), post_servings.getText().toString(),post_imageUrl.getText().toString(), post_author.getText().toString());

                manager = new RequestManager(PostActivity.this);
                manager.getPostRecipe(postRecipeListener, postGetSet);
            }
        });
    }

    final PostRecipeListener postRecipeListener = new PostRecipeListener() {
        @Override
        public void didSuccess(ResultPost resultPost, String message) {
            sourceUri = resultPost.url;
            Toast.makeText(PostActivity.this, sourceUri, Toast.LENGTH_SHORT).show();
            post_title.setText("");
            post_ingredients.setText("");
            post_instructions.setText("");
            post_time.setText("");
            post_servings.setText("");
            post_imageUrl.setText("");
            post_author.setText("");
            storeImage = new StoreImage();
            database = RoomDb.getInstance(PostActivity.this);
            storeImage.setUri(sourceUri);
            Intent intent = new Intent(PostActivity.this, PostedImage.class);
            database.mainDao().insert(storeImage);
            list = database.mainDao().getAll();
            setResult(Activity.RESULT_OK, intent);
            startActivity(intent);
            finish();
        }

        @Override
        public void didFailure(String message) {
            Toast.makeText(PostActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };
}
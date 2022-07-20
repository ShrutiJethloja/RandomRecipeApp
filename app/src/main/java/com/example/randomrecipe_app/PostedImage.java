package com.example.randomrecipe_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.randomrecipe_app.Adapters.CustomImageAdapter;
import com.example.randomrecipe_app.Database.RoomDb;
import com.example.randomrecipe_app.Database.StoreImage;
import com.example.randomrecipe_app.Listeners.UriActionListener;

import java.util.ArrayList;
import java.util.List;

public class PostedImage extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    RecyclerView recyclerView;
    CustomImageAdapter customImageAdapter;
    List<StoreImage> list = new ArrayList<>();
    RoomDb database;
    StoreImage selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted_image);

        //text_uri = findViewById(R.id.text_uri);
        recyclerView = findViewById(R.id.recycler_image);

        database = RoomDb.getInstance(this);
        list = database.mainDao().getAll();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customImageAdapter = new CustomImageAdapter(PostedImage.this, list, uriActionListener);
        recyclerView.setAdapter(customImageAdapter);
    }

    private final UriActionListener uriActionListener = new UriActionListener() {
        @Override
        public void onLongclick(StoreImage storeImage, CardView cardView) {
            selectedImage = new StoreImage();
            selectedImage = storeImage;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(this, cardView);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                database.mainDao().delete(selectedImage);
                list.remove(selectedImage);
                customImageAdapter.notifyDataSetChanged();
                Toast.makeText(PostedImage.this, "Image is deleted!!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return false;
        }
    }
}
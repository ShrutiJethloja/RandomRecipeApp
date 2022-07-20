package com.example.randomrecipe_app.Listeners;

import androidx.cardview.widget.CardView;

import com.example.randomrecipe_app.Database.StoreImage;

public interface UriActionListener {
    public void onLongclick(StoreImage storeImage, CardView cardView);
}

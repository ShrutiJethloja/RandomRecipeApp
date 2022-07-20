package com.example.randomrecipe_app.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "StoreImage")
public class StoreImage implements Serializable {
    @PrimaryKey
    @NonNull
    String uri;

    @NonNull
    public String getUri() {
        return uri;
    }

    public void setUri(@NonNull String uri) {
        this.uri = uri;
    }

}

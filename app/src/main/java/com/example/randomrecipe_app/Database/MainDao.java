package com.example.randomrecipe_app.Database;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDao {
    @Insert(onConflict = IGNORE)
    void insert(StoreImage storeImage);

    @Query("SELECT  * FROM StoreImage")
    List<StoreImage> getAll();

    @Delete
    void delete(StoreImage storeImage);
}

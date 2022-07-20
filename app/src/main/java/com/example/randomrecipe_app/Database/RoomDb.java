package com.example.randomrecipe_app.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = StoreImage.class, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase{
        private static RoomDb database;
        private static String DATABASE_NAME = "PostImage";

        public synchronized static RoomDb getInstance(Context context) {
            if(database ==  null) {
                database = Room.databaseBuilder(context.getApplicationContext(), RoomDb.class,
                        DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
            }
            return database;
        }

        public abstract MainDao mainDao();
}

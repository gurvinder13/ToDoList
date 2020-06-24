package com.example.androidscreening.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidscreening.model.ItemsList;

@Database(entities = {ItemsList.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract Dao myDao();

}

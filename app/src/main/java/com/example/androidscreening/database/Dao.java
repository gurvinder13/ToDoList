package com.example.androidscreening.database;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidscreening.model.ItemsList;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert
    void addData(ItemsList itemsList);

    @Query("select * from ItemsList ORDER BY isDone")
    List<ItemsList> getItemsData();

    @Query("UPDATE Itemslist SET isDone=:isFinished WHERE id = :rowId")
    void update(int rowId, boolean isFinished);
}

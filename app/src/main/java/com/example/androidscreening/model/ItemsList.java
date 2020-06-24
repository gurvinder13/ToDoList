package com.example.androidscreening.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Itemslist")
public class ItemsList {
    @PrimaryKey(autoGenerate = true)
    private int id;

        @ColumnInfo(name = "title")
        private String title;

        @ColumnInfo(name = "description")
        private String description;

        @ColumnInfo(name = "image")
        private String image;

        @ColumnInfo(name = "isDone")
        private boolean isDone;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

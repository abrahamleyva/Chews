package edu.csumb.abmedina.roomthre;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "author")
    private String mAuthor;


    // constructor
    public Book(String title, String author) {
        mTitle = title;
        mAuthor = author;
    }

    // getters required for Room

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    // setters for Room
    public void setId(int id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }


    @Override
    public String toString() {
        String result = String.format("Book [id = %d, title = %s, author = %s", mId, mTitle, mAuthor);
        return result;
    }
}
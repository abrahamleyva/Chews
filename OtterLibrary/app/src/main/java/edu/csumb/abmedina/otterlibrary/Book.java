package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Abraham on 12/5/2017.
 */

@Entity(tableName = "book")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "title")
    private String mTitle;

    @ColumnInfo(name = "author")
    private String mAuthor;

    @ColumnInfo(name = "isnb")
    private String mIsnb;

    @ColumnInfo(name = "fee")
    private double mFee;

    public Book(String title, String author, String isnb, double fee) {
        mTitle = title;
        mAuthor = author;
        mIsnb = isnb;
        mFee = fee;
    }

    // getters and setters

    public int getId() { return mId; }

    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getIsnb() {
        return mIsnb;
    }

    public double getFee() {
        return mFee;
    }

    public void setId(int id) { mId = id; }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public void setIsnb(String isnb) {
        mIsnb = isnb;
    }

    public void setFee(double fee) {
        mFee = fee;
    }

    @Override
    public String toString() {
        String result = String.format("Book [id = %d, title = %s, author = %s, isnb = %s, fee = %f]"
                , mId, mTitle, mAuthor, mIsnb, mFee);
        return result;
    }

}

package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Abraham on 12/5/2017.
 */

@Entity(tableName = "hold")
public class Hold {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "user_id")
    private int mUserId;

    @ColumnInfo(name = "book_id")
    private int mBookId;

    @ColumnInfo(name = "pick_date")
    private Date mPickDate;

    @ColumnInfo(name = "drop_date")
    private Date mDropDate;

    @ColumnInfo(name = "active")
    private int mActive;

    public Hold(int userId, int bookId, Date pickDate, Date dropDate, int active) {
        mUserId = userId;
        mBookId = bookId;
        mPickDate = pickDate;
        mDropDate = dropDate;
        mActive = active;
    }

    // getter and setters

    public int getId() {
        return mId;
    }

    public int getUserId() {
        return mUserId;
    }

    public int getBookId() {
        return mBookId;
    }

    public Date getPickDate() {
        return mPickDate;
    }

    public Date getDropDate() {
        return mDropDate;
    }

    public int getActive() { return mActive; }

    public void setId(int id) {
        mId = id;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public void setBookId(int bookId) {
        mBookId = bookId;
    }

    public void setPickDate(Date pickDate) {
        mPickDate = pickDate;
    }

    public void setDropDate(Date dropDate) {
        mDropDate = dropDate;
    }

    public void setActive(int active) { mActive = active; }

    @Override
    public String toString() {
        String result = String.format("Hold [id = %d, userId = %d, bookId = %d, active = %d]", mId, mUserId, mBookId, mActive);
        return result;
    }

}

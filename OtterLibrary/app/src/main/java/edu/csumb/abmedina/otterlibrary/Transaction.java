package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Abraham on 12/5/2017.
 */

@Entity(tableName = "transaction")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "type")
    private String mType;

    @ColumnInfo(name = "userId")
    private int mUserId;

    @ColumnInfo(name = "hold_id")
    private int mHoldId;

    @ColumnInfo(name = "trans_date")
    private Date mTransDate;

    public Transaction(String type, int userId, int holdId, Date transDate) {
        mType = type;
        mUserId = userId;
        mHoldId = holdId;
        mTransDate = transDate;
    }

    // getters and setters

    public int getId() { return mId; }

    public String getType() { return mType; }

    public int getUserId() { return mUserId; }

    public int getHoldId() { return mHoldId; }

    public Date getTransDate() { return mTransDate; }

    public void setId(int id) {
        mId = id;
    }

    public void setType(String type) {
        mType = type;
    }

    public void setUserId(int userId) { mUserId = userId; }

    public void setHoldId(int holdId) {
        mHoldId = holdId;
    }

    public void setTransDate(Date date) {
        mTransDate = date;
    }

    @Override
    public String toString() {
        String result = String.format("Transaction [id = %d, type = %s, userId = %d, holdId = %d]", mId, mType, mUserId, mHoldId);
        return result;
    }

}
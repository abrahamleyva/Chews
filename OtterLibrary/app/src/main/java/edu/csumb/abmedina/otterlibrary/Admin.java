package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Abraham on 12/5/2017.
 */

@Entity(tableName = "admin")
public class Admin {

    @PrimaryKey(autoGenerate = true)
    private int mId;

    @ColumnInfo(name = "username")
    private String mUsername;

    @ColumnInfo(name = "password")
    private String mPassword;

    public Admin(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    // getters and setters

    public int getId() {
        return mId;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public String toString() {
        String result = String.format("Admin [id = %d, username = %s, password = %s]", mId, mUsername, mPassword);
        return result;
    }
}

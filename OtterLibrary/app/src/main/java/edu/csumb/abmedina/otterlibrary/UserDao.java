package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Abraham on 12/3/2017.
 */

@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Query("select count(*) from user")
    int count();

    @Query("SELECT * FROM user WHERE mId = :userId")
    public User getUser(int userId);

    @Query("SELECT * FROM user")
    public List<User> getAllUsers();

    @Update
    public void updateUser(User user);

    @Delete
    public void deleteUser(User user);
}

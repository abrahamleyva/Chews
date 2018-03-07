package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Abraham on 12/5/2017.
 */

@Dao
public interface AdminDao {

    @Insert
    public void addAdmin(Admin admin);

    @Query("select count(*) from admin")
    int count();

    @Query("SELECT * FROM admin WHERE mId = :userId")
    public User getAdmin(int userId);

    @Query("SELECT * FROM admin")
    public List<Admin> getAllAdmins();

    @Update
    public void updateAdmin(Admin admin);

    @Delete
    public void deleteAdmin(Admin admin);
}

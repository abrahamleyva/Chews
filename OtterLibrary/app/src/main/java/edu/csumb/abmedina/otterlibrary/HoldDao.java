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
public interface HoldDao {

    @Insert
    public void addHold(Hold hold);

    @Query("select count(*) from hold")
    int count();

    @Query("SELECT * FROM hold WHERE mId = :holdId")
    public Hold getHold(int holdId);

    @Query("SELECT * FROM hold")
    public List<Hold> getAllHolds();

    @Query("update hold set active = 0 where mId = :holdId")
    public void deactivate(int holdId);

    @Delete
    public void deleteHold(Hold hold);

}

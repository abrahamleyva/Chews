package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Abraham on 12/6/2017.
 */

@Dao
public interface TransactionDao {
    @Insert
    public void addTransaction(Transaction transaction);

    @Query("select count(*) from 'transaction'")
    int count();

    @Query("SELECT * FROM 'transaction' WHERE mId = :transactionId")
    public Transaction getTransaction(int transactionId);

    @Query("SELECT * FROM `transaction`")
    public List<Transaction> getAllTransactions();

    @Update
    public void updateTransaction(Transaction transaction);

    @Delete
    public void deleteTransaction(Transaction transaction);
}

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
public interface BookDao {

    @Insert
    public void addBook(Book book);

    @Query("select count(*) from book")
    int count();

    @Query("SELECT * FROM book WHERE mId = :bookId")
    public Book getBook(int bookId);

    @Query("SELECT * FROM book")
    public List<Book> getAllBooks();

    @Update
    public void updateBook(Book book);

    @Delete
    public void deleteBook(Book book);
}
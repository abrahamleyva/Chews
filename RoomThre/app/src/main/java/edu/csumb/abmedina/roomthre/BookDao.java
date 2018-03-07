package edu.csumb.abmedina.roomthre;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BookDao {

    @Insert
    public void addBook(Book book);


    @Query("SELECT * FROM books WHERE mId = :bookId")
    public Book getBook(int bookId);


    @Query("SELECT * FROM books")
    public List<Book> getAllBooks();


    @Update
    public void updateBook(Book book);

    @Delete
    public void deleteBook(Book book);

}
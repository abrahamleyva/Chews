package edu.csumb.abmedina.roomthre;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private BookDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = BookDatabase.getInstance(this);

        db.bookDao().addBook(new Book("Ender's Game", "Orsen Scott Card"));
        db.bookDao().addBook(new Book("Absolute Java", "Walter Savitch"));

        List<Book> bookList = db.bookDao().getAllBooks();

        Log.d(TAG, String.valueOf(bookList.get(0)));
        Log.d(TAG, String.valueOf(bookList.get(1)));
    }
}

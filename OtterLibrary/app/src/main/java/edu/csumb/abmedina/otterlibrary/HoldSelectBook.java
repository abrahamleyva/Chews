package edu.csumb.abmedina.otterlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class HoldSelectBook extends AppCompatActivity {

    ListView bookList;

    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hold_select_book);

        db = LibraryDatabase.getInstance(this);

        Bundle data = getIntent().getExtras();

        if(data != null) {

            Calendar myPick = new GregorianCalendar(data.getInt("pickYear"),
                    (data.getInt("pickMonth") + 11) % 12,
                    data.getInt("pickDay"),
                    data.getInt("pickHour"),
                    data.getInt("pickMinute"));
            Calendar myDrop = new GregorianCalendar(data.getInt("dropYear"),
                    (data.getInt("dropMonth") + 11) % 12,
                    data.getInt("dropDay"),
                    data.getInt("dropHour"),
                    data.getInt("dropMinute"));

            List<Hold> holdList = db.holdDao().getAllHolds();
            List<Book> allBooks = db.bookDao().getAllBooks();
            final ArrayList<Integer> bookKeys = new ArrayList<>();

            for(int i = 0; i < allBooks.size(); i++) {
                bookKeys.add(allBooks.get(i).getId());
            }

            for(int i = 0; i < holdList.size(); i++) {
                Calendar tempPick = Calendar.getInstance();
                Calendar tempDrop = Calendar.getInstance();
                tempPick.setTime(holdList.get(i).getPickDate());
                tempDrop.setTime(holdList.get(i).getDropDate());
                if(holdList.get(i).getActive() == 1) {
                    if(myPick.getTimeInMillis() <= tempPick.getTimeInMillis() &&
                            myDrop.getTimeInMillis() >= tempPick.getTimeInMillis()) {
                        bookKeys.removeAll(Arrays.asList(holdList.get(i).getBookId()));
                    } else if(myPick.getTimeInMillis() >= tempPick.getTimeInMillis() &&
                            myDrop.getTimeInMillis() <= tempDrop.getTimeInMillis()) {
                        bookKeys.removeAll(Arrays.asList(holdList.get(i).getBookId()));
                    } else if(myPick.getTimeInMillis() <= tempDrop.getTimeInMillis() &&
                            myDrop.getTimeInMillis() >= tempDrop.getTimeInMillis()) {
                        bookKeys.removeAll(Arrays.asList(holdList.get(i).getBookId()));
                    } else if(myPick.getTimeInMillis() <= tempPick.getTimeInMillis() &&
                            myDrop.getTimeInMillis() >= tempDrop.getTimeInMillis()) {
                        bookKeys.removeAll(Arrays.asList(holdList.get(i).getBookId()));
                    }
                }
            }

            if(bookKeys.size() == 0) {
                prompt("Error","There are no books available for the selected dates.");
            }

            bookList = findViewById(R.id.book_list);

            String[] books = new String[bookKeys.size()];

            for(int i = 0; i < bookKeys.size(); i++) {
                Book temp = db.bookDao().getBook(bookKeys.get(i));
                books[i] = "Book Title: " + String.valueOf(temp.getTitle()) + "\n" +
                        "Author: " + String.valueOf(temp.getAuthor()) + "\n" +
                        "ISBN: " + String.valueOf(temp.getIsnb()) + "\n" +
                        "Hourly Fee: $" + String.valueOf(temp.getFee());
            }

            ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, books);
            bookList.setAdapter(myAdapter);

            bookList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(HoldSelectBook.this, UserCreateHoldLogin.class);
                            Bundle data = getIntent().getExtras();

                            if(data != null) {
                                data.putInt("bookId", bookKeys.get(i));
                                intent.putExtras(data);
                                startActivity(intent);
                                close();
                            }
                        }
                    }
            );
        }
    }

    public void prompt(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        close();
                    }}).show();
    }

    public void close() {
        this.finish();
    }
}

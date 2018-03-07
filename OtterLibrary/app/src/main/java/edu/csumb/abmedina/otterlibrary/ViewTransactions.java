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
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ViewTransactions extends AppCompatActivity {

    ListView transactionListView;

    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transactions);

        SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy HH:mm");

        db = LibraryDatabase.getInstance(this);

        Bundle data = getIntent().getExtras();

        if(data != null) {

            List<Transaction> transactionList = db.transactionDao().getAllTransactions();

            transactionListView = findViewById(R.id.transaction_listView);

            String[] transactions = new String[transactionList.size()];

            for(int i = 0; i < transactionList.size(); i++) {
                User temp = db.userDao().getUser(transactionList.get(i).getUserId());

                if(transactionList.get(i).getType().equals("New Account")) {
                    transactions[i] = "Transaction type: New Account" + "\n" +
                            "Customer’s username: " + String.valueOf(temp.getUsername()) + "\n" +
                            "Transaction date/time: " + String.valueOf(format1.format(transactionList.get(i).getTransDate()));
                } else if(transactionList.get(i).getType().equals("Create Hold")) {
                    Hold hold = db.holdDao().getHold(transactionList.get(i).getHoldId());
                    Book book = db.bookDao().getBook(hold.getBookId());
                    transactions[i] = "Transaction type: Create Hold" + "\n" +
                            "Customer’s username: " + String.valueOf(temp.getUsername()) + "\n" +
                            "Book title: " + String.valueOf(book.getTitle()) + "\n" +
                            "PickUp date/time: " + String.valueOf(format1.format(hold.getPickDate())) + "\n" +
                            "Return date/time: " + String.valueOf(format1.format(hold.getDropDate())) + "\n" +
                            "Reservation number: " + String.valueOf(hold.getId()) + "\n" +
                            "Transaction date/time: " + String.valueOf(format1.format(transactionList.get(i).getTransDate()));
                } else if(transactionList.get(i).getType().equals("Cancel Hold")) {
                    Hold hold = db.holdDao().getHold(transactionList.get(i).getHoldId());
                    Book book = db.bookDao().getBook(hold.getBookId());
                    transactions[i] = "Transaction type: Cancel Hold" + "\n" +
                            "Customer’s username: " + String.valueOf(temp.getUsername()) + "\n" +
                            "Book title: " + String.valueOf(book.getTitle()) + "\n" +
                            "PickUp date/time: " + String.valueOf(format1.format(hold.getPickDate())) + "\n" +
                            "Return date/time: " + String.valueOf(format1.format(hold.getDropDate())) + "\n" +
                            "Reservation number: " + String.valueOf(hold.getId()) + "\n" +
                            "Transaction date/time: " + String.valueOf(format1.format(transactionList.get(i).getTransDate()));
                }
            }

            ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, transactions);
            transactionListView.setAdapter(myAdapter);

            prompt("Add Book", "Would you like to add a new book?");
        }
    }

    public void prompt(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent intent = new Intent(ViewTransactions.this, AddBook.class);
                        startActivity(intent);
                        close();
                    }})
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    public void close() {
        this.finish();
    }
}
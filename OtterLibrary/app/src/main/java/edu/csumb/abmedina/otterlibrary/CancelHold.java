package edu.csumb.abmedina.otterlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class CancelHold extends AppCompatActivity {

    ListView myHoldList;

    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_hold);

        db = LibraryDatabase.getInstance(this);

        Bundle data = getIntent().getExtras();

        if(data != null) {

            List<Hold> holdList = db.holdDao().getAllHolds();
            final ArrayList<Integer> holdKeys = new ArrayList<>();

            for(int i = 0; i < holdList.size(); i++) {
                if(holdList.get(i).getActive() == 1 && holdList.get(i).getUserId() == data.getInt("userId")) {
                    holdKeys.add(holdList.get(i).getId());
                }
            }

            if(holdKeys.size() == 0) {
                prompt("Error","You have no active holds.");
            }

            myHoldList = findViewById(R.id.hold_list);

            String[] holds = new String[holdKeys.size()];

            SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy HH:mm");

            for(int i = 0; i < holdKeys.size(); i++) {
                Hold temp = db.holdDao().getHold(holdKeys.get(i));

                holds[i] = "Reservation Number: " + temp.getId() + "\n" +
                        "Pick Up: " + format1.format(temp.getPickDate()) + "\n" +
                        "Return: " + format1.format(temp.getDropDate()) + "\n" +
                        "Title: " + db.bookDao().getBook(temp.getBookId()).getTitle();
            }

            ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, holds);
            myHoldList.setAdapter(myAdapter);

            myHoldList.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            db.holdDao().deactivate(holdKeys.get(i));
                            db.transactionDao().addTransaction(new Transaction("Cancel Hold",
                                    db.holdDao().getHold(holdKeys.get(i)).getUserId(), holdKeys.get(i), new Date()));
                            prompt("Success", "Hold Canceled.");
                        }
                    }
            );
        }
    }

    public void prompt(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        close();
                    }}).show();
    }

    public void close() {
        this.finish();
    }
}

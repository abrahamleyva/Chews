package edu.csumb.abmedina.otterlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserCreateHoldLogin extends AppCompatActivity {

    EditText username;
    EditText password;
    Button submitButton;

    private int fails = 0;
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_hold_login);

        db = LibraryDatabase.getInstance(this);

        username = findViewById(R.id.username_editText);
        password = findViewById(R.id.password_editText);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                Bundle data = getIntent().getExtras();

                List<User> userList = db.userDao().getAllUsers();

                double total = 0;

                for(int i = 0; i < userList.size(); i++) {
                    if(String.valueOf(userList.get(i).getUsername()).equals(usernameValue) &&
                            String.valueOf(userList.get(i).getPassword()).equals(passwordValue)) {

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

                            total = (myDrop.getTimeInMillis() - myPick.getTimeInMillis()) / 3600000;

                            db.holdDao().addHold(new Hold(userList.get(i).getId(),
                                    data.getInt("bookId"), myPick.getTime(), myDrop.getTime(), 1));
                            db.transactionDao().addTransaction(new Transaction("Create Hold",
                                    userList.get(i).getId(), db.holdDao().count(), new Date()));

                            fails = -1;

                            SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy HH:mm");

                            created("Success","Username: " + userList.get(i).getUsername() + "\n" +
                                    "Pick Up: " + format1.format(myPick.getTime()) + "\n" +
                                    "Return: " + format1.format(myDrop.getTime()) + "\n" +
                                    "Title: " + db.bookDao().getBook(data.getInt("bookId")).getTitle() + "\n" +
                                    "Reservation Number: " + db.holdDao().count() + "\n" +
                                    "Total: $" + String.format("%.2f", total * db.bookDao().getBook(data.getInt("bookId")).getFee()));
                        }
                    }
                }

                fails++;
                if(fails > 0) {
                    prompt("Error","Incorrect user credentials.");
                }
            }
        });

    }

    public void prompt(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(fails > 1) {
                            close();
                        }
                    }}).show();
    }

    public void created(String title, String message) {
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

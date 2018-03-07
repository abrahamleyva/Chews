package edu.csumb.abmedina.otterlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class CreateAccount extends AppCompatActivity {

    EditText username;
    EditText password;
    Button submitButton;

    private static final String TAG = "CreateAccount";
    private int fails = 0;
    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        db = LibraryDatabase.getInstance(this);

        username = findViewById(R.id.create_username_editText);
        password = findViewById(R.id.create_password_editText);
        submitButton = findViewById(R.id.submit_user_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                List<User> userList = db.userDao().getAllUsers();

                int test = fails;

                if(!valid(usernameValue)) {
                    fails++;
                    prompt("Error" ,"Username criteria not met");
                } else if(!valid(passwordValue)) {
                    fails++;
                    prompt("Error","Password criteria not met");
                } else {
                    for(int i = 0; i < userList.size(); i++) {
                        if(String.valueOf(userList.get(i).getUsername()).equals(usernameValue)) {
                            fails++;
                            prompt("Error","Username attempted is already taken");
                        }
                    }
                }

                if(test == fails) {
                    fails = 2;
                    db.userDao().addUser(new User(usernameValue, passwordValue));
                    db.transactionDao().addTransaction(new Transaction("New Account",
                            db.userDao().count(), -1, new Date()));
                    prompt("Success","Username was successfully created");
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

    public boolean valid(String value) {
        boolean letter = false;
        boolean number = false;
        boolean special = false;

        for(int i = 0; i < value.length(); i++) {
            char current = value.charAt(i);

            if(Character.isLetter(current)) {
                letter = true;
            } else if(Character.isDigit(current)) {
                number = true;
            } else if(current == '!' || current == '@' || current == '#' || current == '$') {
                special = true;
            }
        }

        return letter && number && special;
    }

    public void close() {
        this.finish();
    }
}

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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class UserCancelHoldLogin extends AppCompatActivity {

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

                List<User> userList = db.userDao().getAllUsers();

                for(int i = 0; i < userList.size(); i++) {
                    if(String.valueOf(userList.get(i).getUsername()).equals(usernameValue) &&
                            String.valueOf(userList.get(i).getPassword()).equals(passwordValue)) {

                        Intent intent = new Intent(UserCancelHoldLogin.this, CancelHold.class);
                        Bundle data =  new Bundle();

                        data.putInt("userId", userList.get(i).getId());

                        intent.putExtras(data);
                        startActivity(intent);
                        close();
                        fails = -1;
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

    public void close() {
        this.finish();
    }
}

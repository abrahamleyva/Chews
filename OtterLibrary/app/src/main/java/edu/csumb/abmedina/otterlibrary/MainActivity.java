package edu.csumb.abmedina.otterlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button createAccountButton;
    Button placeHoldButton;
    Button cancelHoldButton;
    Button manageSystemButton;

    private static final String TAG = "MainActivity";

    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = LibraryDatabase.getInstance(this);
        db.populateInitialData();

        createAccountButton = findViewById(R.id.create_account_button);
        placeHoldButton = findViewById(R.id.place_hold_button);
        cancelHoldButton = findViewById(R.id.cancel_hold_button);
        manageSystemButton = findViewById(R.id.manage_system_button);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        placeHoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScheduleHold.class);
                startActivity(intent);
            }
        });

        cancelHoldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserCancelHoldLogin.class);
                startActivity(intent);
            }
        });

        manageSystemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdminLogin.class);
                startActivity(intent);
            }
        });
    }
}

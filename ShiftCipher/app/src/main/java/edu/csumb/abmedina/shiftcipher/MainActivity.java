package edu.csumb.abmedina.shiftcipher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText myMessage;
    EditText myShift;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myMessage = findViewById(R.id.string);
        myShift = findViewById(R.id.shift);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                try {
                    int shiftValue = Integer.parseInt(myShift.getText().toString());
                    String message = myMessage.getText().toString();

                    if(shiftValue > 25 || shiftValue < 0) {
                        int temp = Integer.parseInt("error");
                    }

                    Intent intent = new Intent(MainActivity.this, Result.class);
                    Bundle data =  new Bundle();

                    data.putString("message", message);
                    data.putInt("shift", shiftValue);

                    intent.putExtras(data);
                    startActivity(intent);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

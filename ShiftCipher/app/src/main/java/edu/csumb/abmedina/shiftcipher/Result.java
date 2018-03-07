package edu.csumb.abmedina.shiftcipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result = findViewById(R.id.result);

        Bundle data = getIntent().getExtras();

        if(data != null) {
            String message = data.getString("message");
            int shift = data.getInt("shift");

            char partition[] = new char[message.length()];

            for(int i = 0; i < message.length(); i++) {
                partition[i] = message.charAt(i);
            }

            message = "";

            for(int i = 0; i < partition.length; i++) {
                if(Character.isLowerCase(partition[i])) {
                    message += (char) ((partition[i] - 97 + shift) % 26 + 97);
                } else if(Character.isUpperCase(partition[i])) {
                    message += (char) ((partition[i] - 65 + shift) % 26 + 65);
                }
            }

            result.setText(message);
        }
    }
}

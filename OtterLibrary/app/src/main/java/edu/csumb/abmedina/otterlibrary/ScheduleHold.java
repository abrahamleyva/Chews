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

public class ScheduleHold extends AppCompatActivity {

    EditText pickMonth;
    EditText pickDay;
    EditText pickYear;
    EditText pickHour;
    EditText pickMinute;
    EditText dropMonth;
    EditText dropDay;
    EditText dropYear;
    EditText dropHour;
    EditText dropMinute;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_hold);

        pickMonth = findViewById(R.id.pick_month_editText);
        pickDay = findViewById(R.id.pick_day_editText);
        pickYear = findViewById(R.id.pick_year_editText);
        pickHour = findViewById(R.id.pick_hour_editText);
        pickMinute = findViewById(R.id.pick_minute_editText);
        dropMonth = findViewById(R.id.drop_month_editText);
        dropDay = findViewById(R.id.drop_day_editText);
        dropYear = findViewById(R.id.drop_year_editText);
        dropHour = findViewById(R.id.drop_hour_editText);
        dropMinute = findViewById(R.id.drop_minute_editText);
        confirm = findViewById(R.id.confirm_schedule_button);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar pickUp = new GregorianCalendar(Integer.parseInt(pickYear.getText().toString()),
                        Integer.parseInt(pickMonth.getText().toString()),
                        Integer.parseInt(pickDay.getText().toString()),
                        Integer.parseInt(pickHour.getText().toString()),
                        Integer.parseInt(pickMinute.getText().toString()));

                Calendar dropOff = new GregorianCalendar(Integer.parseInt(dropYear.getText().toString()),
                        Integer.parseInt(dropMonth.getText().toString()),
                        Integer.parseInt(dropDay.getText().toString()),
                        Integer.parseInt(dropHour.getText().toString()),
                        Integer.parseInt(dropMinute.getText().toString()));

                if(dropOff.getTimeInMillis() - pickUp.getTimeInMillis() > 604800000) {
                    prompt("Error","Books cannot be checked out longer than 7 days.");
                } else {
                    Intent intent = new Intent(ScheduleHold.this, HoldSelectBook.class);
                    Bundle data =  new Bundle();

                    data.putInt("pickMonth", Integer.parseInt(pickMonth.getText().toString()));
                    data.putInt("pickDay", Integer.parseInt(pickDay.getText().toString()));
                    data.putInt("pickYear", Integer.parseInt(pickYear.getText().toString()));
                    data.putInt("pickHour", Integer.parseInt(pickHour.getText().toString()));
                    data.putInt("pickMinute", Integer.parseInt(pickMinute.getText().toString()));
                    data.putInt("dropMonth", Integer.parseInt(dropMonth.getText().toString()));
                    data.putInt("dropDay", Integer.parseInt(dropDay.getText().toString()));
                    data.putInt("dropYear", Integer.parseInt(dropYear.getText().toString()));
                    data.putInt("dropHour", Integer.parseInt(dropHour.getText().toString()));
                    data.putInt("dropMinute", Integer.parseInt(dropMinute.getText().toString()));

                    intent.putExtras(data);
                    startActivity(intent);
                    close();
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

                    }}).show();
    }

    public void close() {
        this.finish();
    }
}

package edu.csumb.abmedina.otterlibrary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class AddBook extends AppCompatActivity {

    EditText titleEditText;
    EditText authorEditText;
    EditText isbnEditText;
    EditText feeEditText;
    Button submitButton;

    private LibraryDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        db = LibraryDatabase.getInstance(this);

        titleEditText = findViewById(R.id.title_editText);
        authorEditText = findViewById(R.id.author_editText);
        isbnEditText = findViewById(R.id.isbn_editText);
        feeEditText = findViewById(R.id.fee_editText);
        submitButton = findViewById(R.id.submit_book_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fail = false;
                String titleValue = titleEditText.getText().toString();
                String authorValue = authorEditText.getText().toString();
                String isbnValue = isbnEditText.getText().toString();
                String feeValue = feeEditText.getText().toString();

                List<Book> bookList = db.bookDao().getAllBooks();

                for(int i = 0; i < bookList.size(); i++) {
                    if(titleValue.equals(bookList.get(i).getTitle())) {
                        fail = true;
                        prompt("Error", "Invalid book information.");
                    }
                }

                if(!fail) {
                    db.bookDao().addBook(new Book(titleValue, authorValue, isbnValue, Double.parseDouble(feeValue)));
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
                        close();
                    }}).show();
    }

    public void close() {
        this.finish();
    }
}

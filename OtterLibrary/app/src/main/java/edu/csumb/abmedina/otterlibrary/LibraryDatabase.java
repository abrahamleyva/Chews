package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.Date;
import java.util.GregorianCalendar;

@Database(entities = {User.class, Book.class, Admin.class, Hold.class, Transaction.class}, version = 15, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class LibraryDatabase extends RoomDatabase {

    private static LibraryDatabase INSTANCE;
    public abstract UserDao userDao();
    public abstract BookDao bookDao();
    public abstract AdminDao adminDao();
    public abstract HoldDao holdDao();
    public abstract TransactionDao transactionDao();

    private static final Object sLock = new Object();

    public static LibraryDatabase getInstance(Context context) {

        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        LibraryDatabase.class, "library.db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build();
            }
            return INSTANCE;
        }
    }

    public void populateInitialData() {

        if (userDao().count() == 0) {

            beginTransaction();

            try{
                userDao().addUser(new User("a@lice5", "@csit100"));
                userDao().addUser(new User("$brian7", "123abc##"));
                userDao().addUser(new User("!chris12!", "CHRIS12!!"));
                setTransactionSuccessful();

            } finally {
                endTransaction();
            }
        }

        if (bookDao().count() == 0) {

            beginTransaction();

            try{
                bookDao().addBook(new Book("The 42nd Parallel", "John Dos Passos", "123-ABC-101", 0.05));
                bookDao().addBook(new Book("White Teeth", "Zadie Smith", "ABCDEF-09", 1.00));
                bookDao().addBook(new Book("For Whom the Bell Tolls", "Ernest Hemingway", "CDE-777-123", 0.25));

                setTransactionSuccessful();

            } finally {
                endTransaction();
            }
        }

        if (adminDao().count() == 0) {

            beginTransaction();

            try{
                adminDao().addAdmin(new Admin("!admin2", "!admin2"));

                setTransactionSuccessful();

            } finally {
                endTransaction();
            }
        }
    }
}
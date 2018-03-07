package edu.csumb.abmedina.roomthre;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Book.class}, version = 1, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase INSTANCE;
    public abstract BookDao bookDao();



    private static final Object sLock = new Object();

    public static BookDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BookDatabase.class, "books.db").
                        allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
}
package edu.csumb.abmedina.otterlibrary;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {

        if (timestamp == null) {
            return null;
        } else {
            return new Date(timestamp);
        }
    }

    @TypeConverter
    public static Long toTimeStamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
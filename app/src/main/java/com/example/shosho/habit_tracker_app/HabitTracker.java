package com.example.shosho.habit_tracker_app;


import android.provider.BaseColumns;

public class HabitTracker {

    public static final class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME= "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME ="name";
        public final static String COLUMN_START_DATE = "date";
        public final static String COLUMN_NUMBER_OF_TIMES = "number_of_times";
    }
}

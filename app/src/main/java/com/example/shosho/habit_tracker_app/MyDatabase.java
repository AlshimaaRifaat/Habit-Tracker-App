package com.example.shosho.habit_tracker_app;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shosho.habit_tracker_app.HabitTracker.HabitEntry;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String name_of_database = "database for habbit tracker";
    private static final int version = 1;

    public MyDatabase(Context context) {
        super(context, name_of_database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABITS_TABLE =  "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_START_DATE + " DATETIME NOT NULL DEFAULT CURRENT_DATE, "
                + HabitEntry.COLUMN_NUMBER_OF_TIMES + " INTEGER NOT NULL DEFAULT 0);";


        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

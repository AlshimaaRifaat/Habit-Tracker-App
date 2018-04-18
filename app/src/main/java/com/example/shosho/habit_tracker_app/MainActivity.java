package com.example.shosho.habit_tracker_app;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.shosho.habit_tracker_app.HabitTracker.HabitEntry;


public class MainActivity extends AppCompatActivity {

    private MyDatabase data_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       Intent intent = new Intent(MainActivity.this, Edition_Activity.class);
                                       startActivity(intent);
                                   }
        });

        data_base = new MyDatabase(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }

    private Cursor read() {
        SQLiteDatabase db = data_base.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_NAME,
                HabitEntry.COLUMN_START_DATE,
                HabitEntry.COLUMN_NUMBER_OF_TIMES
        };

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null, null, null);
        return cursor;
    }

    private void displayDatabase() {
        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.text_view1);

        try {
            displayView.setText("The habit tracker  include : " + cursor.getCount() + " rows.\n\n");
            displayView.append(HabitEntry._ID + "   " +
                    HabitEntry.COLUMN_NAME + "   " +
                    HabitEntry.COLUMN_START_DATE + "   " +
                    HabitEntry.COLUMN_NUMBER_OF_TIMES + "   " + "\n");

            int id_position = cursor.getColumnIndex(HabitTracker.HabitEntry._ID);
            int name_position = cursor.getColumnIndex(HabitTracker.HabitEntry.COLUMN_NAME);
            int date_position= cursor.getColumnIndex(HabitTracker.HabitEntry.COLUMN_START_DATE);
            int times_poition = cursor.getColumnIndex(HabitTracker.HabitEntry.COLUMN_NUMBER_OF_TIMES);

            while (cursor.moveToNext()) {
                int your_ID = cursor.getInt(id_position);
                String your_Name = cursor.getString(name_position);
                String present_date = cursor.getString(date_position);
                int your_numbers_of_times = cursor.getInt(times_poition);

                displayView.append(("\n" + your_ID + "    " +
                        your_Name + "    " +
                        present_date + "    " +
                        your_numbers_of_times));
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void insert() {
        SQLiteDatabase db = data_base.getWritableDatabase();
        ContentValues my_value1 = new ContentValues();
        ContentValues my_value2 = new ContentValues();
        my_value1.put(HabitEntry.COLUMN_NAME, "eat cheese");
        my_value1.put(HabitEntry.COLUMN_NUMBER_OF_TIMES, 1);
        my_value2.put(HabitEntry.COLUMN_NAME, "drink juice");
        my_value2.put(HabitEntry.COLUMN_NUMBER_OF_TIMES, 2);
        db.insert(HabitEntry.TABLE_NAME, null, my_value1);
        db.insert(HabitEntry.TABLE_NAME, null, my_value2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_your_data:
                insert();
                displayDatabase();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

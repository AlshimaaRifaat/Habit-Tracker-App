package com.example.shosho.habit_tracker_app;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.shosho.habit_tracker_app.HabitTracker.HabitEntry;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Edition_Activity extends AppCompatActivity {

    @BindView(R.id.edit_text_name)
    EditText mNameEditText;
    @BindView(R.id.edit_text_number_of_times)
    EditText times_number;
    @BindView(R.id.text_view_date)
    TextView mDate;
    private String present_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);
        ButterKnife.bind(this);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        present_date =  sdf.format(c.getTime());
        Log.d("Edit", present_date);
        mDate.setText(present_date);
    }

    private void insertHabit(){

        String nameString = mNameEditText.getText().toString().trim();
        String numberOfTimesString = times_number.getText().toString().trim();
        int numberOfTimes = 0;
        if(!"".equals(numberOfTimesString))
            numberOfTimes = Integer.parseInt(numberOfTimesString);

        MyDatabase mDbHelper = new MyDatabase(this);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_NAME, nameString);
        values.put(HabitEntry.COLUMN_START_DATE, present_date);
        values.put(HabitEntry.COLUMN_NUMBER_OF_TIMES, numberOfTimes);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "habit is not saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Habit saved with id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


    public void save_my_habit(View view) {
        if(mNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "you must enter your habit name", Toast.LENGTH_SHORT).show();
            return;
        }
        insertHabit();
        finish();
    }
    public void cancel_MY_Habit(View view) {
        finish();
    }
}

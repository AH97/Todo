package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FocusView extends AppCompatActivity {

    TaskDB db;

    ArrayList<Task> tasks;
    ArrayAdapter<String> tasksAdapter;

    Calendar calendar;
    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_view);
        calendar = Calendar.getInstance();

        db = new TaskDB(this);

        Intent intent = new Intent();
        String selectedDate = intent.getStringExtra("selectedDate");
        try {
            calendar.setTime(df.parse(selectedDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tasks = db.readAll(cal.getTimeInMillis());
        tasksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2);
        for(int i=0; i<tasks.size(); i++) {
            tasksAdapter.add(tasks.get(i).getName());
            tasksAdapter.add(tasks.get(i).getDescription());
        }

    }
}

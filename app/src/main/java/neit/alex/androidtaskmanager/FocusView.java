package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_view);
        calendar = Calendar.getInstance();

        listView = (ListView) findViewById(R.id.lv_tasks);

        db = new TaskDB(this);

        Intent intent = new Intent();
        String selectedDate = intent.getStringExtra("selectedDate");

        tasks = db.readAll(selectedDate);
        tasksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i=0; i<tasks.size(); i++) {
            tasksAdapter.add(tasks.get(i).getName());
        }
        listView.setAdapter(tasksAdapter);

    }
}

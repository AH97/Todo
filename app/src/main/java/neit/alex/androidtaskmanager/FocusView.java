package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FocusView extends AppCompatActivity {

    TaskDB db;

    ArrayList<Task> tasks;
    ArrayAdapter<String> tasksAdapter;

    Calendar calendar;
    Date date;
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
        String selectedDate = getIntent().getStringExtra("selectedDate");

        tasks = db.readAll(selectedDate);
        tasksAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i=0; i<tasks.size(); i++) {
            tasksAdapter.add(tasks.get(i).getName());
        }
        listView.setAdapter(tasksAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),TaskInfo.class);

                intent.putExtra("selectedTask", db.read(tasks.get(i).getId()));
                getApplicationContext().startActivity(intent);
            }
        });
    }
}

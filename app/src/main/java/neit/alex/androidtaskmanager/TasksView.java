package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TasksView extends AppCompatActivity {

    static final int NEW_TASK = 10;
    static final int TASK_INFO = 20;

    TaskDB db;

    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
    static ArrayList<Calendar> dates;
    static ArrayList<Task> tasks;
    static ArrayAdapter<String> datesAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.lv_dates);

        db = new TaskDB(this);
        dates = db.readAllDates();

        datesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for (int i=0; i<dates.size(); i++) {
            datesAdapter.add(df.format(dates.get(i).getTime()));
        }
        listView.setAdapter(datesAdapter);


        // List view item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),FocusView.class);

                intent.putExtra("selectedDate", df.format(dates.get(i)));
                getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_addNew) {
            Intent i = new Intent(this, NewTaskForm.class);
            startActivityForResult(i, NEW_TASK);
        }
        else if (id == R.id.menu_refresh) {
            Intent intent = new Intent(getApplicationContext(),TasksView.class);
            getApplicationContext().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_TASK) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Toast notif = Toast.makeText(this, data.getStringExtra("return"), Toast.LENGTH_SHORT);
                notif.setGravity(Gravity.CENTER|Gravity.BOTTOM, 0, 0);
                notif.show();

                Intent intent = new Intent(getApplicationContext(),TasksView.class);
                getApplicationContext().startActivity(intent);
            }
        }
    }
}

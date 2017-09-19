package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TaskInfo extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    ListView listView;
    TextView name, desc, date, time;
    Button edit, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        listView = (ListView) findViewById(R.id.lv_taskInfo);

        Intent intent = new Intent();
        Task task = getIntent().getExtras().getParcelable("selectedTask");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add(task.getName());
        adapter.add(task.getDescription());
        adapter.add("Event on " + task.getDate());
        adapter.add("Event at " + task.getTime());

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_done) {

        }
        else if (id == R.id.menu_edit) {

        }
        else if (id == R.id.menu_delete) {

        }

        return super.onOptionsItemSelected(item);
    }
}

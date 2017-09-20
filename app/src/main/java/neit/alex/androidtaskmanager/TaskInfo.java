package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TaskInfo extends AppCompatActivity {

    TaskDB db;
    Task task;
    TextView titleView, dueInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        titleView = (TextView) findViewById(R.id.titleView);
        dueInfo = (TextView) findViewById(R.id.dueInfo);

        Intent intent = new Intent();
        Task task = intent.getExtras().getParcelable("selectedTask");

        titleView.setText(task.getName());
        dueInfo.setText("Task due by " + task.getDate().toString() + " at " + task.getTime().toString());
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

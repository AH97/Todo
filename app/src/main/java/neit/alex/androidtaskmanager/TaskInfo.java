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

    static final int TASK_UPD = 10;

    TaskDB db;
    Task task;
    TextView titleView, dueInfo;

    int taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        db = new TaskDB(this);

        titleView = (TextView) findViewById(R.id.titleView);
        dueInfo = (TextView) findViewById(R.id.dueInfo);

        Intent intent = new Intent();
        taskId = getIntent().getExtras().getInt("selectedTask");
        task = db.read(taskId);

        String info = "Task due by " + task.getDate() + " at " + task.getTime();
        titleView.setText(task.getName());
        dueInfo.setText(info);
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
            db.setDone(task.getId());
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        else if (id == R.id.menu_edit) {
            Intent i = new Intent(this, NewTaskForm.class);
            i.putExtra("taskUpd", task);
            startActivityForResult(i, TASK_UPD);
        }
        else if (id == R.id.menu_delete) {
            db.destroy(task);
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}

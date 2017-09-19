package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class TaskInfo extends AppCompatActivity {

    TextView name, desc, date, time;
    Button edit, done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        

        Intent intent = new Intent();
        Task task = intent.getParcelableExtra("selectedTask");


    }
}

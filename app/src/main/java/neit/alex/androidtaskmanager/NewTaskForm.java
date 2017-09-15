package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.sql.Time;

public class NewTaskForm extends AppCompatActivity {


    Task task;

    EditText name, desc, date, time;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);

        name = (EditText) findViewById(R.id.txt_name);
        desc = (EditText) findViewById(R.id.txt_desc);
        date = (EditText) findViewById(R.id.txt_setDate);
        time = (EditText) findViewById(R.id.txt_setTime);
        submit = (Button) findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                task = new Task();

                task.setName(name.getText().toString());
                task.setDescription(desc.getText().toString());
                task.setDate(Date.valueOf(date.getText().toString()));
                task.setTime(Time.valueOf(time.getText().toString()));
            }
        });
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("returnKey1", "Swinging on a star. ");
        data.putExtra("returnKey2", "You could be better then you are. ");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}

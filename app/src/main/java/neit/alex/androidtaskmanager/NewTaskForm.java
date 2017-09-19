package neit.alex.androidtaskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewTaskForm extends AppCompatActivity {

    TaskDB db;

    Task task;

    String strTime;
    String strDate;

    EditText name, desc, date, time;
    Button submit;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_form);
        db = new TaskDB(this);



        name = (EditText) findViewById(R.id.txt_name);
        desc = (EditText) findViewById(R.id.txt_desc);
        date = (EditText) findViewById(R.id.txt_setDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentDate = Calendar.getInstance();
                int year = currentDate.get(Calendar.YEAR);
                int month = currentDate.get(Calendar.MONTH);
                int day = currentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(NewTaskForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selYear, int selMonth, int selDay) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(selYear, selMonth, selDay);

                        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
                        strDate = df.format(newDate.getTime());
                        date.setText(df.format(newDate.getTime()));
                    }
                }, year, month, day);
                datePicker.setTitle("Set Date");
                datePicker.show();
            }
        });

        time = (EditText) findViewById(R.id.txt_setTime);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR);
                int minute = currentTime.get(Calendar.MINUTE);

                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(NewTaskForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selHour, int selMin) {
                        Calendar newTime = Calendar.getInstance();
                        newTime.set(Calendar.HOUR, selHour);
                        newTime.set(Calendar.MINUTE, selMin);

                        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
                        strTime = df.format(newTime.getTime());
                        time.setText(df.format(newTime.getTime()));
                    }
                }, hour, minute, false);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        submit = (Button) findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                task = new Task();

                task.setName(name.getText().toString());
                task.setDescription(desc.getText().toString());
                task.setDate(strDate);
                task.setTime(strTime);

                db.create(task);
                finish();
            }
        });
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();
        data.putExtra("return", "new task added.");
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        super.finish();
    }
}

package neit.alex.androidtaskmanager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditTaskForm extends AppCompatActivity {

    TaskDB db;
    Task task;
    int taskId = -1;

    //controls
    TextView resultView;
    EditText taskName;
    Button setDate, setTime;

    //calendar and formatting
    Calendar calendar = Calendar.getInstance();
    final Calendar currentDate = Calendar.getInstance();
    final Calendar currentTime = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");
    SimpleDateFormat tf = new SimpleDateFormat("hh:mm a");

    //build output strings here
    String preText = "Task set for ";
    String strTime;
    String strDate;

    //declaring int vars for current date and time
    int year, month, day, hour, minute;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        db = new TaskDB(this);

        Intent i = new Intent();
        task = db.read(i.getExtras().getInt("upTask"));

        //setting default picker values
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH);
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        hour = currentTime.get(Calendar.HOUR);
        minute = currentTime.get(Calendar.MINUTE);

        taskName = (EditText) findViewById(R.id.txt_taskName);
        setDate = (Button) findViewById(R.id.btn_setDate);
        setTime = (Button) findViewById(R.id.btn_setTime);
        resultView = (TextView) findViewById(R.id.resultView);

        strDate = task.getDate();
        strTime = task.getTime();

        taskName.setText(task.getName());


        // DATE PICKER BUTTON CLICK LISTENER
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker;
                datePicker = new DatePickerDialog(EditTaskForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int selYear, int selMonth, int selDay) {

                        calendar.set(selYear, selMonth, selDay);
                        strDate = df.format(calendar.getTime());

                        //setting result
                        if ( !resultView.getText().toString().equals("") ) {
                            resultView.setText("Task set for " + strDate + " at " + strTime);
                        }
                        else {
                            resultView.setText("Task set for " + strDate);
                        }

                    }
                }, year, month, day);
                datePicker.setTitle("Set Date");
                datePicker.show();
            }
        });
        // TIME PICKER BUTTON CLICK LISTENER
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePicker;
                timePicker = new TimePickerDialog(EditTaskForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selHour, int selMin) {

                        calendar.set(Calendar.HOUR, selHour);
                        calendar.set(Calendar.MINUTE, selMin);
                        strTime = tf.format(calendar.getTime());

                        //setting result
                        if ( !resultView.getText().toString().equals("") ) {
                            resultView.setText("Task set for " + strDate + " at " + strTime);
                        }
                        else {
                            resultView.setText("Task set for " + strTime);
                        }

                    }
                }, hour, minute, false);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if ( task.getId() != 0 ) {
                    task.setName(taskName.getText().toString());
                    task.setDate(strDate);
                    task.setTime(strTime);

                    db.update(task);
                }
                else {
                    task = new Task();
                    task.setName(taskName.getText().toString());
                    task.setDate(strDate);
                    task.setTime(strTime);

                    db.create(task);
                }

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

package neit.alex.androidtaskmanager;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class FocusView extends AppCompatActivity {

    ArrayList<Task> tasks;
    ArrayAdapter<String> tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_view);

        Intent i = new Intent();
        Calendar cal = (Calendar) i.getSerializableExtra("calendar");



    }
}

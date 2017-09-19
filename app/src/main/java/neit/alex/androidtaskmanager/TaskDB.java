package neit.alex.androidtaskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Alex on 9/14/2017.
 */

public class TaskDB extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "Tasks.db";
    private static final String TABLE = "tasks";

    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_DESC = "description";
    private static final String COL_DATE = "date";
    private static final String COL_TIME = "time";
    private static final String COL_DONE = "isDone";

    public TaskDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_DESC + " TEXT," +
                COL_DATE + " INTEGER," +
                COL_TIME + " INTEGER," +
                COL_DONE + " INTEGER " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(Task task) {

        ContentValues values = new ContentValues();
        values.put(COL_NAME, task.getName());
        values.put(COL_DESC, task.getDescription());
        values.put(COL_DATE, task.getDate());
        values.put(COL_TIME, task.getTime());
        values.put(COL_DONE, getIntBoolean(task.getDone()));

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE, null, values);
        db.close();
    }

    public Task update(Task task) {
        return new Task(); //literally just some fucking dummy return so I don't have to
                            // see the error !
    }

    public void destroy(int id) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE + " WHERE " + COL_ID + " = " + id);
    }

    public Task read(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE + " WHERE " + COL_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);

        if ( cursor != null ) {
            cursor.moveToFirst();
        }

        Task task = new Task();
        task.setId(cursor.getInt(0));
        task.setName(cursor.getString(1));
        task.setDescription(cursor.getString(2));
        task.setDateLong(cursor.getLong(3));
        task.setTimeLong(cursor.getLong(4));
        task.setDone(getBoolean(cursor.getInt(5)));

        return task;
    }

    public ArrayList<Calendar> readAllDates() {
        SQLiteDatabase db = getWritableDatabase();

        //formatting
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMMM dd, yyyy");


        ArrayList<Calendar> dates = new ArrayList<>();

        String query = "SELECT DISTINCT " + COL_DATE + " FROM " + TABLE + " ORDER BY " + COL_DATE + " ASC";
        Cursor cursor = db.rawQuery(query, null);

        if ( cursor.moveToFirst() ) {
            do {
                cal.setTimeInMillis(cursor.getLong(0));
                dates.add(cal);
            } while ( cursor.moveToNext() );
        }

        return dates;
    }

    public ArrayList<Task> readAll() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<Task> tasks = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if ( cursor.moveToFirst() ) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setDescription(cursor.getString(2));
                task.setDateLong(cursor.getLong(3));
                task.setTimeLong(cursor.getLong(4));
                task.setDone(getBoolean(cursor.getInt(5)));

                tasks.add(task);
            } while ( cursor.moveToNext() );
        }

        return tasks;
    }

    /*
            HELPER FUNCTIONS
     */

    public int getIntBoolean(boolean value) {
        return (value ? 1 : 0);
    }

    public boolean getBoolean(int value) {
        return (value != 0);
    }
}

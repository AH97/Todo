package neit.alex.androidtaskmanager.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import neit.alex.androidtaskmanager.Scope.Task;

/**
 * Created by Alex on 9/14/2017.
 */

public class TaskDB extends SQLiteOpenHelper {

    private static final int VERSION = 5;
    private static final String DB_NAME = "Tasks.db";
    private static final String TABLE = "tasks";

    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
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
                COL_DATE + " TEXT," +
                COL_TIME + " TEXT," +
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
        values.put(COL_DATE, task.getDate());
        values.put(COL_TIME, task.getTime());
        values.put(COL_DONE, getIntBoolean(task.getDone()));

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE, null, values);
        db.close();
    }

    public void update(Task task) {

        ContentValues values = new ContentValues();
        values.put(COL_NAME, task.getName());
        values.put(COL_DATE, task.getDate());
        values.put(COL_TIME, task.getTime());

        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE, values, COL_ID + " = ?", new String[]{Integer.toString(task.getId())});
        db.close();
    }

    public int setDone(int id) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_DONE, 1);

        return db.update(TABLE, values, COL_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int destroy(Task task) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete(TABLE, COL_ID + " = ?", new String[]{String.valueOf(task.getId())});
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
        task.setDate(cursor.getString(2));
        task.setTime(cursor.getString(3));
        task.setDone(getBoolean(cursor.getInt(4)));

        return task;
    }

    public ArrayList<Task> readAll() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = db.query(false, TABLE, new String[]{COL_ID,COL_NAME,COL_DATE,COL_TIME,COL_DONE}, COL_DONE + "=?",new String[]{String.valueOf(0)},null,null,null,null);

        if ( cursor.moveToFirst() ) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setDate(cursor.getString(2));
                task.setTime(cursor.getString(3));
                task.setDone(getBoolean(cursor.getInt(4)));

                tasks.add(task);
            } while ( cursor.moveToNext() );
        }

        return tasks;
    }

    public ArrayList<Task> readAllWithDone() {
        SQLiteDatabase db = getWritableDatabase();

        ArrayList<Task> tasks = new ArrayList<>();

        Cursor cursor = db.query(false, TABLE, new String[]{COL_ID,COL_NAME,COL_DATE,COL_TIME,COL_DONE}, null,null,null,null,null,null);

        if ( cursor.moveToFirst() ) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                task.setName(cursor.getString(1));
                task.setDate(cursor.getString(2));
                task.setTime(cursor.getString(3));
                task.setDone(getBoolean(cursor.getInt(4)));

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

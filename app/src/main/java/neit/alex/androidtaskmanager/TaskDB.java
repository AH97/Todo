package neit.alex.androidtaskmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public TaskDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " +
                TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_DESC + " TEXT," +
                COL_DATE + " TEXT," +
                COL_TIME + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}

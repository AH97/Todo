package neit.alex.androidtaskmanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alex on 9/14/17.
 */

public class Task implements Parcelable {

    private Calendar dCal;
    private Calendar tCal;

    private int id;
    private String name;
    private String description;
    private long date;
    private long time;
    private Boolean isDone = false;

    public Task() {
        isDone = false;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(int year, int month, int day) {
        dCal.add(Calendar.YEAR, year);
        dCal.add(Calendar.MONTH, month);
        dCal.add(Calendar.DAY_OF_MONTH, day);

        this.date = dCal.getTimeInMillis();
    }

    public void setDateLong(long date) {
        this.date = date;
    }

    public long getTime() {
        return time;
    }

    public void setTime(int hour, int minute) {
        tCal.add(Calendar.HOUR, hour);
        tCal.add(Calendar.MINUTE, minute);

        this.time = tCal.getTimeInMillis();
    }

    public void setTimeLong(long time) {
        this.time = time;
    }

    /*

            PARCELABLE IMPLEMENTATION

     */

    protected Task(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        date = in.readLong();
        time = in.readLong();
        isDone = in.readInt() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(date);
        dest.writeLong(time);
        dest.writeInt((isDone ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}

package neit.alex.androidtaskmanager;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;
import java.util.Date;

/**
 * Created by alex on 9/14/17.
 */

public class Task implements Parcelable {

    private int id;
    private String name;
    private String description;
    private Date date;
    private Time time;

    public Task() {

    }

    protected Task(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    /*

        Parcelable Implementation

     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
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

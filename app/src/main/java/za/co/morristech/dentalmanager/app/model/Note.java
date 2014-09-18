package za.co.morristech.dentalmanager.app.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by wademorris on 2014/09/10.
 */
public class Note implements Serializable, Parcelable {

    private static final String TAG = Note.class.getName();
    private static final long serialVersionUID = 3325822159148818227L;

    private String content;
    private int id;
    private int patientId;
    private int severity;
    private long timestamp;
    private int toothId;

    public Note(int n, int n2, int n3, int n4, String string, long l) {
        this.id = n;
        this.patientId = n2;
        this.toothId = n3;
        this.severity = n4;
        this.content = string;
        this.timestamp = l;
    }

    public Note(int n, int n2, int n3, String string) {
        this.patientId = n;
        this.toothId = n2;
        this.severity = n3;
        this.content = string;
    }

    private Note(Parcel in) {
        this.content = in.readString();
        this.id = in.readInt();
        this.patientId = in.readInt();
        this.severity = in.readInt();
        this.timestamp = in.readLong();
        this.toothId = in.readInt();
    }

    public String getContent() {
        return this.content;
    }

    public int getId() {
        return this.id;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public int getSeverity() {
        return this.severity;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int getToothId() {
        return this.toothId;
    }

    public void setContent(String string) {
        this.content = string;
    }

    public void setSeverity(int n) {
        this.severity = n;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeInt(this.id);
        dest.writeInt(this.patientId);
        dest.writeInt(this.severity);
        dest.writeLong(this.timestamp);
        dest.writeInt(this.toothId);
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}

package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Cursors implements Parcelable {

    private String before;
    private String after;

    public Cursors() {
        this.before = null;
        this.after = null;
    }

    public Cursors(String before, String after) {
        this.before = before;
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    protected Cursors(Parcel in) {
        before = in.readString();
        after = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(before);
        dest.writeString(after);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cursors> CREATOR = new Creator<Cursors>() {
        @Override
        public Cursors createFromParcel(Parcel in) {
            return new Cursors(in);
        }

        @Override
        public Cursors[] newArray(int size) {
            return new Cursors[size];
        }
    };
}

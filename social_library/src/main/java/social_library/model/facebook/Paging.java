package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Paging implements Parcelable {

    private String previous;
    private String next;

    public Paging(String previous, String next) {
        this.previous = previous;
        this.next = next;
    }

    public Paging() {
        this.previous = null;
        this.next = null;
    }

    protected Paging(Parcel in) {
        previous = in.readString();
        next = in.readString();
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(previous);
        dest.writeString(next);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Paging> CREATOR = new Creator<Paging>() {
        @Override
        public Paging createFromParcel(Parcel in) {
            return new Paging(in);
        }

        @Override
        public Paging[] newArray(int size) {
            return new Paging[size];
        }
    };
}

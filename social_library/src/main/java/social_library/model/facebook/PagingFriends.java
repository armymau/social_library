package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class PagingFriends implements Parcelable {

    private Cursors cursors;

    public PagingFriends() {
        this.cursors = null;
    }

    public PagingFriends(Cursors cursors) {
        this.cursors = cursors;
    }

    public Cursors getCursors() {
        return cursors;
    }

    public void setCursors(Cursors cursors) {
        this.cursors = cursors;
    }

    protected PagingFriends(Parcel in) {
        cursors = in.readParcelable(Cursors.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(cursors, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PagingFriends> CREATOR = new Creator<PagingFriends>() {
        @Override
        public PagingFriends createFromParcel(Parcel in) {
            return new PagingFriends(in);
        }

        @Override
        public PagingFriends[] newArray(int size) {
            return new PagingFriends[size];
        }
    };
}

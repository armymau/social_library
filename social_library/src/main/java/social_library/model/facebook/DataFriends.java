package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class DataFriends implements Parcelable {

    private String name;
    private String id;

    public DataFriends() {
        this.name = null;
        this.id = null;
    }

    public DataFriends(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Creator<DataFriends> getCREATOR() {
        return CREATOR;
    }

    protected DataFriends(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataFriends> CREATOR = new Creator<DataFriends>() {
        @Override
        public DataFriends createFromParcel(Parcel in) {
            return new DataFriends(in);
        }

        @Override
        public DataFriends[] newArray(int size) {
            return new DataFriends[size];
        }
    };
}

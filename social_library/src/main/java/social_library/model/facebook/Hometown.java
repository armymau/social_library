package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Hometown implements Parcelable {

    private String id;
    private String name;

    public Hometown() {
        this.id = null;
        this.name = null;
    }

    public Hometown(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<Hometown> getCREATOR() {
        return CREATOR;
    }

    protected Hometown(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Hometown> CREATOR = new Creator<Hometown>() {
        @Override
        public Hometown createFromParcel(Parcel in) {
            return new Hometown(in);
        }

        @Override
        public Hometown[] newArray(int size) {
            return new Hometown[size];
        }
    };
}

package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Target implements Parcelable {

    private String id;
    private String url;

    public Target(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public Target() {
        this.id = null;
        this.url = null;
    }

    protected Target(Parcel in) {
        id = in.readString();
        url = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Target> CREATOR = new Creator<Target>() {
        @Override
        public Target createFromParcel(Parcel in) {
            return new Target(in);
        }

        @Override
        public Target[] newArray(int size) {
            return new Target[size];
        }
    };
}

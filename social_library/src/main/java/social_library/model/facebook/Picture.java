package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture implements Parcelable {

    private Data data;

    public Picture() {
        this.data = null;
    }

    public Picture(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static Creator<Picture> getCREATOR() {
        return CREATOR;
    }

    protected Picture(Parcel in) {
        data = in.readParcelable(Data.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(data, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel in) {
            return new Picture(in);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}

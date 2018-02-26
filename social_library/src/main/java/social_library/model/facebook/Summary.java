package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Summary implements Parcelable {

    private int total_count;

    public Summary() {
        this.total_count = 0;
    }

    public Summary(int total_count) {
        this.total_count = total_count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    protected Summary(Parcel in) {
        total_count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total_count);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Summary> CREATOR = new Creator<Summary>() {
        @Override
        public Summary createFromParcel(Parcel in) {
            return new Summary(in);
        }

        @Override
        public Summary[] newArray(int size) {
            return new Summary[size];
        }
    };
}

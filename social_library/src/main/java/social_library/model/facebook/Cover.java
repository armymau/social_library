package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Cover implements Parcelable {

    private String id;
    private int offset_y;
    private String source;

    public Cover() {
        this.id = null;
        this.offset_y = 0;
        this.source = null;
    }

    public Cover(String id, int offset_y, String source) {
        this.id = id;
        this.offset_y = offset_y;
        this.source = source;
    }

    protected Cover(Parcel in) {
        id = in.readString();
        offset_y = in.readInt();
        source = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(offset_y);
        dest.writeString(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cover> CREATOR = new Creator<Cover>() {
        @Override
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        @Override
        public Cover[] newArray(int size) {
            return new Cover[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOffset_y() {
        return offset_y;
    }

    public void setOffset_y(int offset_y) {
        this.offset_y = offset_y;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

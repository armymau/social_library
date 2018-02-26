package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {

    private int height;
    private String src;
    private int width;

    public Image(int height, String src, int width) {
        this.height = height;
        this.src = src;
        this.width = width;
    }

    public Image() {
        this.height = 0;
        this.src = null;
        this.width = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    protected Image(Parcel in) {
        height = in.readInt();
        src = in.readString();
        width = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(height);
        dest.writeString(src);
        dest.writeInt(width);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}

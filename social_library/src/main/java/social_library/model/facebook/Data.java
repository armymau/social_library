package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {

    private boolean is_silhouette;
    private String url;

    public Data() {
        this.is_silhouette = false;
        this.url = null;
    }

    public Data(boolean is_silhouette, String url) {
        this.is_silhouette = is_silhouette;
        this.url = url;
    }

    public boolean is_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(boolean is_silhouette) {
        this.is_silhouette = is_silhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected Data(Parcel in) {
        is_silhouette = in.readByte() != 0;
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (is_silhouette ? 1 : 0));
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}

package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class Media implements Parcelable {

    private Image image;

    public Media(Image image) {
        this.image = image;
    }

    public Media() {
        this.image = null;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    protected Media(Parcel in) {
        image = in.readParcelable(Image.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };
}

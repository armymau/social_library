package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class DataAttachments implements Parcelable {

    private String description;
    private Media media;
    private Target target;
    private String title;
    private String type;
    private String url;

    public DataAttachments(String description, Media media, Target target, String title, String type, String url) {
        this.description = description;
        this.media = media;
        this.target = target;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    public DataAttachments() {
        this.description = null;
        this.media = null;
        this.target = null;
        this.title = null;
        this.type = null;
        this.url = null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected DataAttachments(Parcel in) {
        description = in.readString();
        media = in.readParcelable(Media.class.getClassLoader());
        target = in.readParcelable(Target.class.getClassLoader());
        title = in.readString();
        type = in.readString();
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeParcelable(media, flags);
        dest.writeParcelable(target, flags);
        dest.writeString(title);
        dest.writeString(type);
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataAttachments> CREATOR = new Creator<DataAttachments>() {
        @Override
        public DataAttachments createFromParcel(Parcel in) {
            return new DataAttachments(in);
        }

        @Override
        public DataAttachments[] newArray(int size) {
            return new DataAttachments[size];
        }
    };
}

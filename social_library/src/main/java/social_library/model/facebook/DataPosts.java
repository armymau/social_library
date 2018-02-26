package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

public class DataPosts implements Parcelable {

    private String created_time;
    private String id;
    private String full_picture;
    private String message;
    private String description;
    private Attachments attachments;
    private String story;

    public DataPosts(String created_time, String id, String full_picture, String message, String description, Attachments attachments, String story) {
        this.created_time = created_time;
        this.id = id;
        this.full_picture = full_picture;
        this.message = message;
        this.description = description;
        this.attachments = attachments;
        this.story = story;
    }

    public DataPosts() {
        this.created_time = null;
        this.id = null;
        this.full_picture = null;
        this.message = null;
        this.description = null;
        this.attachments = null;
        this.story = null;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attachments getAttachments() {
        return attachments;
    }

    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public static Creator<DataPosts> getCREATOR() {
        return CREATOR;
    }

    protected DataPosts(Parcel in) {
        created_time = in.readString();
        id = in.readString();
        full_picture = in.readString();
        message = in.readString();
        description = in.readString();
        attachments = in.readParcelable(Attachments.class.getClassLoader());
        story = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(created_time);
        dest.writeString(id);
        dest.writeString(full_picture);
        dest.writeString(message);
        dest.writeString(description);
        dest.writeParcelable(attachments, flags);
        dest.writeString(story);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataPosts> CREATOR = new Creator<DataPosts>() {
        @Override
        public DataPosts createFromParcel(Parcel in) {
            return new DataPosts(in);
        }

        @Override
        public DataPosts[] newArray(int size) {
            return new DataPosts[size];
        }
    };
}

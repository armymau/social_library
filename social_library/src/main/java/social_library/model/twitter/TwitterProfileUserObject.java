package social_library.model.twitter;

import android.os.Parcel;
import android.os.Parcelable;

public class TwitterProfileUserObject implements Parcelable {

    private static final long serialVersionUID = 1L;
    private long twitter_id;
    private String twitter_user;
    private String twitter_name;
    private String twitter_img;
    private String twitter_oauth;
    private String twitter_secret;
    private String cover_img;

    public TwitterProfileUserObject() {
        this.twitter_id = 0;
        this.twitter_user = null;
        this.twitter_name = null;
        this.twitter_img = null;
        this.twitter_oauth = null;
        this.twitter_secret = null;
        this.cover_img = null;
    }

    public TwitterProfileUserObject(long twitter_id, String twitter_user, String twitter_name, String twitter_img, String twitter_oauth, String twitter_secret, String cover_img) {
        this.twitter_id = twitter_id;
        this.twitter_user = twitter_user;
        this.twitter_name = twitter_name;
        this.twitter_img = twitter_img;
        this.twitter_oauth = twitter_oauth;
        this.twitter_secret = twitter_secret;
        this.cover_img = cover_img;
    }

    protected TwitterProfileUserObject(Parcel in) {
        twitter_id = in.readLong();
        twitter_user = in.readString();
        twitter_name = in.readString();
        twitter_img = in.readString();
        twitter_oauth = in.readString();
        twitter_secret = in.readString();
        cover_img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(twitter_id);
        dest.writeString(twitter_user);
        dest.writeString(twitter_name);
        dest.writeString(twitter_img);
        dest.writeString(twitter_oauth);
        dest.writeString(twitter_secret);
        dest.writeString(cover_img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TwitterProfileUserObject> CREATOR = new Creator<TwitterProfileUserObject>() {
        @Override
        public TwitterProfileUserObject createFromParcel(Parcel in) {
            return new TwitterProfileUserObject(in);
        }

        @Override
        public TwitterProfileUserObject[] newArray(int size) {
            return new TwitterProfileUserObject[size];
        }
    };

    public long getTwitter_id() {
        return twitter_id;
    }

    public void setTwitter_id(long twitter_id) {
        this.twitter_id = twitter_id;
    }

    public String getTwitter_user() {
        return twitter_user;
    }

    public void setTwitter_user(String twitter_user) {
        this.twitter_user = twitter_user;
    }

    public String getTwitter_name() {
        return twitter_name;
    }

    public void setTwitter_name(String twitter_name) {
        this.twitter_name = twitter_name;
    }

    public String getTwitter_img() {
        return twitter_img;
    }

    public void setTwitter_img(String twitter_img) {
        this.twitter_img = twitter_img;
    }

    public String getTwitter_oauth() {
        return twitter_oauth;
    }

    public void setTwitter_oauth(String twitter_oauth) {
        this.twitter_oauth = twitter_oauth;
    }

    public String getTwitter_secret() {
        return twitter_secret;
    }

    public void setTwitter_secret(String twitter_secret) {
        this.twitter_secret = twitter_secret;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }
}

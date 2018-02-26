package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by armando on 07/11/16.
 */

public class FacebookObject implements Parcelable {

    private String id;
    private Picture picture;
    private Cover cover;
    private Posts posts;
    private Friends friends;
    private String about;
    private String birthday;
    private Hometown hometown;


    public FacebookObject() {
        this.id = null;
        this.picture = null;
        this.cover = null;
        this.posts = null;
        this.friends = null;
        this.about = null;
        this.birthday = null;
        this.hometown = null;
    }

    public FacebookObject(String id, Picture picture, Cover cover, Posts posts, Friends friends, String about, String birthday, Hometown hometown) {
        this.id = id;
        this.picture = picture;
        this.cover = cover;
        this.posts = posts;
        this.friends = friends;
        this.about = about;
        this.birthday = birthday;
        this.hometown = hometown;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Hometown getHometown() {
        return hometown;
    }

    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public static Creator<FacebookObject> getCREATOR() {
        return CREATOR;
    }

    protected FacebookObject(Parcel in) {
        id = in.readString();
        picture = in.readParcelable(Picture.class.getClassLoader());
        cover = in.readParcelable(Cover.class.getClassLoader());
        posts = in.readParcelable(Posts.class.getClassLoader());
        friends = in.readParcelable(Posts.class.getClassLoader());
        about = in.readString();
        birthday = in.readString();
        this.hometown = in.readParcelable(Hometown.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(picture, flags);
        dest.writeParcelable(cover, flags);
        dest.writeParcelable(posts, flags);
        dest.writeParcelable(friends, flags);
        dest.writeString(about);
        dest.writeString(birthday);
        dest.writeParcelable(hometown, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FacebookObject> CREATOR = new Creator<FacebookObject>() {
        @Override
        public FacebookObject createFromParcel(Parcel in) {
            return new FacebookObject(in);
        }

        @Override
        public FacebookObject[] newArray(int size) {
            return new FacebookObject[size];
        }
    };
}

package social_library.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.facebook.AccessToken;

public class UserProfileData implements Parcelable {

    private static final long serialVersionUID = 1L;
    private String twitter_userId;
    private String twitter_screenName;
    private String twitter_OauthSecret;
    private String twitter_userToken;
    private String facebook_userId;
    private String facebook_screenName;
    private String facebook_userToken;
    private AccessToken facebook_accessToken;
    private String google_userToken;
    private String mail_userToken;

    public UserProfileData(String twitter_userId, String twitter_screenName, String twitter_OauthSecret, String twitter_userToken, String facebook_userId, String facebook_screenName, String facebook_userToken, AccessToken facebook_accessToken, String google_userToken, String mail_userToken) {
        this.twitter_userId = twitter_userId;
        this.twitter_screenName = twitter_screenName;
        this.twitter_OauthSecret = twitter_OauthSecret;
        this.twitter_userToken = twitter_userToken;
        this.facebook_userId = facebook_userId;
        this.facebook_screenName = facebook_screenName;
        this.facebook_userToken = facebook_userToken;
        this.facebook_accessToken = facebook_accessToken;
        this.google_userToken = google_userToken;
        this.mail_userToken = mail_userToken;
    }

    public UserProfileData() {
        this.twitter_userId = null;
        this.twitter_userToken = null;
        this.twitter_screenName = null;
        this.twitter_OauthSecret = null;
        this.facebook_userId = null;
        this.facebook_screenName = null;
        this.facebook_userToken = null;
        this.facebook_accessToken = null;
        this.google_userToken = null;
        this.mail_userToken = null;
    }

    public AccessToken getFacebook_accessToken() {
        return facebook_accessToken;
    }

    public void setFacebook_accessToken(AccessToken facebook_accessToken) {
        this.facebook_accessToken = facebook_accessToken;
    }

    public String getTwitter_userId() {
        return twitter_userId;
    }

    public void setTwitter_userId(String twitter_userId) {
        this.twitter_userId = twitter_userId;
    }

    public String getTwitter_screenName() {
        return twitter_screenName;
    }

    public void setTwitter_screenName(String twitter_screenName) {
        this.twitter_screenName = twitter_screenName;
    }

    public String getTwitter_userToken() {
        return twitter_userToken;
    }

    public void setTwitter_userToken(String twitter_userToken) {
        this.twitter_userToken = twitter_userToken;
    }

    public String getTwitter_OauthSecret() {
        return twitter_OauthSecret;
    }

    public void setTwitter_OauthSecret(String twitter_OauthSecret) {
        this.twitter_OauthSecret = twitter_OauthSecret;
    }

    public String getFacebook_userId() {
        return facebook_userId;
    }

    public void setFacebook_userId(String facebook_userId) {
        this.facebook_userId = facebook_userId;
    }

    public String getFacebook_screenName() {
        return facebook_screenName;
    }

    public void setFacebook_screenName(String facebook_screenName) {
        this.facebook_screenName = facebook_screenName;
    }

    public String getFacebook_userToken() {
        return facebook_userToken;
    }

    public void setFacebook_userToken(String facebook_userToken) {
        this.facebook_userToken = facebook_userToken;
    }

    public String getGoogle_userToken() {
        return google_userToken;
    }

    public void setGoogle_userToken(String google_userToken) {
        this.google_userToken = google_userToken;
    }

    public String getMail_userToken() {
        return mail_userToken;
    }

    public void setMail_userToken(String mail_userToken) {
        this.mail_userToken = mail_userToken;
    }

    protected UserProfileData(Parcel in) {
        twitter_userId = in.readString();
        twitter_screenName = in.readString();
        twitter_OauthSecret = in.readString();
        twitter_userToken = in.readString();
        facebook_userId = in.readString();
        facebook_screenName = in.readString();
        facebook_userToken = in.readString();
        facebook_accessToken = in.readParcelable(AccessToken.class.getClassLoader());
        google_userToken = in.readString();
        mail_userToken = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(twitter_userId);
        dest.writeString(twitter_screenName);
        dest.writeString(twitter_OauthSecret);
        dest.writeString(twitter_userToken);
        dest.writeString(facebook_userId);
        dest.writeString(facebook_screenName);
        dest.writeString(facebook_userToken);
        dest.writeParcelable(facebook_accessToken, flags);
        dest.writeString(google_userToken);
        dest.writeString(mail_userToken);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserProfileData> CREATOR = new Creator<UserProfileData>() {
        @Override
        public UserProfileData createFromParcel(Parcel in) {
            return new UserProfileData(in);
        }

        @Override
        public UserProfileData[] newArray(int size) {
            return new UserProfileData[size];
        }
    };
}

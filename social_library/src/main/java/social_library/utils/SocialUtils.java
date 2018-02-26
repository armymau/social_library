package social_library.utils;

import android.util.Log;

import com.facebook.AccessToken;

import social_library.model.UserProfileData;

public class SocialUtils {

    private static SocialUtils instance;
    private UserProfileData userProfileData;

    public static SocialUtils getInstance() {
        if (instance == null) {
            instance = new SocialUtils();
        }
        return instance;
    }

    private SocialUtils() {
        userProfileData = new UserProfileData();
    }

    public UserProfileData retrieveUserProfileData() {
        return userProfileData;
    }



    /*
    public Twitter getTwitterForService() {

        String access_token = retrieveUserProfileData().getTwitter_userToken();
        String access_token_secret = retrieveUserProfileData().getTwitter_OauthSecret();

        Twitter twitter = null;
        if (access_token != null && access_token_secret != null) {
            AccessToken accessToken = new AccessToken(access_token, access_token_secret);
            twitter = new TwitterFactory(getTwitterConfiguration()).getInstance(accessToken);
        }
        return twitter;
    }

    public Twitter getTwitterInstance() {
        TwitterFactory factory = new TwitterFactory(SocialUtils.getInstance().getTwitterConfiguration());
        return factory.getInstance();
    }

    public Configuration getTwitterConfiguration() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(SocialConstants.TWITTER_API_KEY);
        builder.setOAuthConsumerSecret(SocialConstants.TWITTER_API_SECRET);
        return builder.build();
    }

    public boolean isTwitterLoggedInAlready(Context context) {
        return SharedPreferencesHelper.getBooleanPreference(context, SocialConstants.PREF_KEY_TWITTER_LOGIN);
    }

    public void setTwitterLoggedInAlready(Context context, boolean twitterLoggedInAlready) {
        SharedPreferencesHelper.setBooleanPreference(context, SocialConstants.PREF_KEY_TWITTER_LOGIN, twitterLoggedInAlready);
    }

    public void updateTwitterUserData(AccessToken accessToken) {

        Log.e(SocialConstants.TAG, "Twitter User Id --> " + accessToken.getUserId());
        Log.e(SocialConstants.TAG, "Twitter Screen Name --> " + accessToken.getScreenName());
        Log.e(SocialConstants.TAG, "Twitter Token --> " + accessToken.getToken());
        Log.e(SocialConstants.TAG, "Twitter Token Secret --> " + accessToken.getTokenSecret());

        userProfileData.setTwitter_userId(String.valueOf(accessToken.getUserId()));
        userProfileData.setTwitter_screenName(String.valueOf(accessToken.getScreenName()));
        userProfileData.setTwitter_userToken(accessToken.getToken());
        userProfileData.setTwitter_OauthSecret(accessToken.getTokenSecret());
    }

    public TwitterProfileUserObject retrieveTwitterUserData() {

        TwitterProfileUserObject twitterProfileUserObject = new TwitterProfileUserObject();
        UserProfileData userProfileData = retrieveUserProfileData();

        twitterProfileUserObject.setTwitter_id(Long.valueOf(userProfileData.getTwitter_userId()));
        twitterProfileUserObject.setTwitter_name(userProfileData.getTwitter_screenName());
        twitterProfileUserObject.setTwitter_oauth(userProfileData.getTwitter_userToken());
        twitterProfileUserObject.setTwitter_secret(userProfileData.getTwitter_OauthSecret());

        return twitterProfileUserObject;
    }



    public void updateMailUserData(AccessToken accessToken) {

        Log.e(SocialConstants.TAG, "Mail OAuth Token --> " + accessToken.getToken());
        userProfileData.setMail_userToken(accessToken.getToken());
    }

    public void setGoogleAccountAuthorized(Context context, String mAccountName) {
        SharedPreferencesHelper.setStringPreference(context, SocialConstants.GOOGLE_ACCOUNT_AUTHORIZED, mAccountName);
    }

    public String getGoogleAccountAuthorized(Context context) {
        return SharedPreferencesHelper.getStringPreference(context, SocialConstants.GOOGLE_ACCOUNT_AUTHORIZED, null);
    }

    public String retrieveUserTokenProfile() {
        UserProfileData userProfileData = retrieveUserProfileData();
        String user_token = null;
        if (userProfileData.getFacebook_userToken() != null) {
            user_token = userProfileData.getFacebook_userToken();
        } else if (userProfileData.getTwitter_userToken() != null) {
            user_token = userProfileData.getTwitter_userToken();
        } else if (userProfileData.getGoogle_userToken() != null) {
            user_token = userProfileData.getGoogle_userToken();
        } else if (userProfileData.getMail_userToken() != null) {
            user_token = userProfileData.getMail_userToken();
        }
        return user_token;
    }

    public void clearSessionFromSocial(Context context) {
        TwitterFactory factory = new TwitterFactory(SocialUtils.getInstance().getTwitterConfiguration());
        Twitter twitter = factory.getInstance();
        try {
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();

            twitter.setOAuthAccessToken(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

    /* facebook */
    public void updateFacebookUserData(String userId, String screenName, String userToken, AccessToken accessToken) {

        Log.e(SocialConstants.TAG, "Facebook User Id --> " + userId);
        Log.e(SocialConstants.TAG, "Facebook Screen Name --> " + screenName);
        Log.e(SocialConstants.TAG, "Facebook Token --> " + userToken);

        userProfileData.setFacebook_userId(userId);
        userProfileData.setFacebook_screenName(screenName);
        userProfileData.setFacebook_userToken(userToken);
        userProfileData.setFacebook_accessToken(accessToken);
    }

    /* twitter */
    public void updateTwitterUserData(String authToken, String tokenSecret, long userId, String userName) {

        Log.e(SocialConstants.TAG, "Twitter User Id --> " + userId);
        Log.e(SocialConstants.TAG, "Twitter Screen Name --> " + userName);
        Log.e(SocialConstants.TAG, "Twitter Token --> " + authToken);

        userProfileData.setTwitter_userId(String.valueOf(userId));
        userProfileData.setTwitter_screenName(String.valueOf(userName));
        userProfileData.setTwitter_userToken(authToken);
        userProfileData.setTwitter_OauthSecret(tokenSecret);
    }

    /* google */
    public void updateGoogleUserData(String accessToken) {

        Log.e(SocialConstants.TAG, "Google OAuth Token --> " + accessToken);
        userProfileData.setGoogle_userToken(accessToken);
    }
}

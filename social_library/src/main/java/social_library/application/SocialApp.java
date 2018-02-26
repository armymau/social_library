package social_library.application;

import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsLogger;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import core.application.CoreApp;
import io.fabric.sdk.android.Fabric;
import social_library.utils.SocialConstants;

public class SocialApp extends CoreApp {

    @Override
    public void onCreate() {
        super.onCreate();

        /* Initialize the FACEBOOK SDK */
        FacebookSdk.sdkInitialize(getApplicationContext());
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.e(SocialConstants.TAG, "FacebookSdk in DEBUG MODE");
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }
        AppEventsLogger.activateApp(this);
        //***************************************************


        /* Initialize the TWITTER SDK */
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(SocialConstants.TWITTER_API_KEY, SocialConstants.TWITTER_API_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        //***************************************************
    }
}

package social_library.social.twitter.client;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import social_library.social.twitter.task.TwitterTask;

public class SocialTwitterApiClient extends TwitterApiClient {

    private static SocialTwitterApiClient instance;

    static {
        instance = new SocialTwitterApiClient(Twitter.getSessionManager().getActiveSession());
    }

    private SocialTwitterApiClient() {
        new SocialTwitterApiClient(Twitter.getSessionManager().getActiveSession());
    }

    private SocialTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public static SocialTwitterApiClient getInstance() {
        if(instance == null) {
            instance = new SocialTwitterApiClient();
        }
        return instance;
    }

    public TwitterTask getUsersService() {
        return getService(TwitterTask.class);
    }
}

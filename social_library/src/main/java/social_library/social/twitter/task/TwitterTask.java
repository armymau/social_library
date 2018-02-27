package social_library.social.twitter.task;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TwitterTask {

    @GET("/1.1/users/show.json")
    Call<User> getPictureFromMyTwitter(@Query("user_id") Long userId,
                                       @Query("screen_name") String screenName,
                                       @Query("include_entities") Boolean includeEntities);

    @GET("/1.1/statuses/user_timeline.json")
    Call<ArrayList<Tweet>> getUserTweetsFromMyTwitter(@Query("user_id") Long userId);






    /*
    public static void createFriendshipStatus(long userId) {
        new CreateFriendshipStatus(userId).execute();
    }

    private void destroyFriendshipStatus(long userId) {
        new DestroyFriendshipStatus(userId).execute();
    }

    public static void favouriteTwitterStatus(long tweet_id) {
        new FavouriteTwitterStatus(tweet_id).execute();
    }

    public static void replyTwitterStatus(long tweet_id) {
        new ReplyTwitterStatus(tweet_id).execute();
    }

    public static void retweetTwitterStatus(long tweet_id) {
        new RetweetTwitterStatus(tweet_id).execute();
    }

    public static void showTwitterStatus(long tweet_id) {
        new ShowTwitterStatus(tweet_id).execute();
    }

    public static void showFriendshipStatus(long my_twitter_id, long userId) {
        //new ShowFriendshipStatus(my_twitter_id, userId).execute();
    }
    */

}

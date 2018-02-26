package social_library.social.twitter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import armymau.it.social_library.R;
import social_library.common.AlertDialogManager;
import social_library.utils.SocialConstants;
import social_library.utils.SocialUtils;

public class TwitterFragment extends Fragment implements View.OnClickListener {

    private TwitterAuthClient mTwitterAuthClient;

    @Override
    public void onStart() {
        super.onStart();
        if (SocialConstants.TWITTER_API_KEY.trim().length() == 0 || SocialConstants.TWITTER_API_SECRET.trim().length() == 0) {
            AlertDialogManager alert = new AlertDialogManager();
            alert.showAlertDialog(getActivity(), "Twitter oAuth tokens", "Please set your twitter oauth tokens first!", false);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTwitterAuthClient = new TwitterAuthClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.twitter_layout, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Object twitterButton;

        if (Button.class.isInstance(view.findViewById(R.id.buttonloginWithTwitter))) {
            twitterButton = view.findViewById(R.id.buttonloginWithTwitter);
            ((Button) twitterButton).setOnClickListener(this);

        } else if (ImageButton.class.isInstance(view.findViewById(R.id.buttonloginWithTwitter))) {
            twitterButton = view.findViewById(R.id.buttonloginWithTwitter);
            ((ImageButton) twitterButton).setOnClickListener(this);

        }
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.buttonloginWithTwitter) {
            twitterLogIn();
        }
    }

    private void twitterLogIn() {
        mTwitterAuthClient.authorize(getActivity(), new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.e(SocialConstants.TAG, result.toString());

                // The TwitterSession is also available through:
                // Twitter.getInstance().core.getSessionManager().getActiveSession()
                TwitterSession session = result.data;
                // TODO: Remove toast and use the TwitterSession's userID
                // with your app's user model

                SocialUtils.getInstance().updateTwitterUserData(session.getAuthToken().token, session.getAuthToken().secret, session.getUserId(), session.getUserName());

                String msg = "@" + session.getUserName() + " logged in! (#" + session.getUserId() + ")";
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e(SocialConstants.TAG, exception.getMessage());
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }
}

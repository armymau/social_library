package social_library.social.facebook.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;

import java.security.MessageDigest;
import java.util.Arrays;

import armymau.it.social_library.R;
import social_library.social.facebook.callback.FacebookResultCallback;

@SuppressLint("ValidFragment")
public class FacebookFragment extends Fragment implements View.OnClickListener {

    private Activity mActivity;
    private CallbackManager callbackManager;

    public FacebookFragment(CallbackManager callbackManager) {
        this.callbackManager = callbackManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();
        try {
            PackageInfo info = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("FB KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facebook_layout, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        FacebookResultCallback callback = new FacebookResultCallback(mActivity);
        LoginManager.getInstance().registerCallback(callbackManager, callback);

        Object fbLoginButton;

        if (Button.class.isInstance(view.findViewById(R.id.buttonloginWithFacebook))) {
            fbLoginButton = view.findViewById(R.id.buttonloginWithFacebook);
            ((Button) fbLoginButton).setOnClickListener(this);

        } else if (ImageButton.class.isInstance(view.findViewById(R.id.buttonloginWithFacebook))) {
            fbLoginButton = view.findViewById(R.id.buttonloginWithFacebook);
            ((ImageButton) fbLoginButton).setOnClickListener(this);

        }
        return view;
    }


    /* View.OnClickListener */
    @Override
    public void onClick(View v) {
        LoginManager.getInstance().logInWithReadPermissions(getActivity(),
                Arrays.asList("email", "user_hometown", "user_religion_politics", "user_status",
                        "user_about_me", "user_likes", "user_tagged_places", "user_location",
                        "user_videos", "user_birthday", "user_photos", "user_website",
                        "user_education_history", "user_posts", "user_work_history",
                        "user_friends", "user_relationship_details", "user_games_activity",
                        "user_relationships", "user_events", "ads_read",
                        "read_page_mailboxes", "user_managed_groups", "user_actions.books",
                        "user_actions.music", "user_actions.video", "user_actions.fitness",
                        "user_actions.news", "read_insights"));
    }
    //***************************************************
}
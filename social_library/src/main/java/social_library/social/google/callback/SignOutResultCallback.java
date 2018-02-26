package social_library.social.google.callback;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import social_library.social.google.fragment.GoogleFragment;
import social_library.utils.SocialConstants;

public class SignOutResultCallback implements ResultCallback<Status> {

    private final Context context;

    public SignOutResultCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResult(@NonNull Status status) {
        ((GoogleFragment) ((AppCompatActivity) context).getSupportFragmentManager().findFragmentByTag(SocialConstants.SOCIAL_GOOGLE_FRAGMENT)).handleSignOutResult(status);
    }
}

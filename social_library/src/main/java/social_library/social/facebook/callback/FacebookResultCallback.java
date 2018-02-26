package social_library.social.facebook.callback;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import social_library.utils.SocialConstants;
import social_library.utils.SocialUtils;

public class FacebookResultCallback implements FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback {

    private final Context context;
    private String facebookAccessTokenString;
    private AccessToken facebookAccessToken;

    public FacebookResultCallback(Context context) {
        this.context = context;
    }


    /* FacebookCallback<LoginResult> */
    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.d(SocialConstants.TAG, "Success Login");
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.e(SocialConstants.TAG, "FacebookSdk in DEBUG MODE");
            Log.e(SocialConstants.TAG, "Access Token: " + loginResult.getAccessToken().getToken());
        }
        facebookAccessTokenString = loginResult.getAccessToken().getToken();
        facebookAccessToken = loginResult.getAccessToken();
        SocialUtils.getInstance().updateFacebookUserData("", "", facebookAccessTokenString, facebookAccessToken);
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), this);
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, name, email, gender, birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onCancel() {
        Toast.makeText(context, "Login Cancel", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
    }
    //***************************************************


    /* GraphRequest.GraphJSONObjectCallback */
    @Override
    public void onCompleted(JSONObject object, GraphResponse response) {
        try {
            String userId = "";
            String screenName = "";
            if (response != null && object != null) {
                userId = object.getString(SocialConstants.FACEBOOK_USER_ID);
                screenName = object.getString(SocialConstants.FACEBOOK_USER_NAME);
            }
            SocialUtils.getInstance().updateFacebookUserData(userId, screenName, facebookAccessTokenString, facebookAccessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //***************************************************
}

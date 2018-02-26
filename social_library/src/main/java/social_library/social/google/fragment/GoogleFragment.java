package social_library.social.google.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Status;

import armymau.it.social_library.R;
import social_library.social.google.callback.RevokeAccessResultCallback;
import social_library.social.google.callback.SignOutResultCallback;
import social_library.social.google.listener.GoogleApiClientConnectionListener;
import social_library.utils.SocialConstants;
import social_library.utils.SocialUtils;

import static android.app.Activity.RESULT_OK;

public class GoogleFragment extends Fragment implements View.OnClickListener {

    private static View view;
    private GoogleApiClient signinClient;
    private GoogleSignInOptions googleSignInOptions;
    private Activity mActivity;
    public static boolean isAutoManageEnabled;

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(signinClient != null && signinClient.isConnected()) {
            if(isAutoManageEnabled())
                signinClient.stopAutoManage((AppCompatActivity) mActivity);
            signinClient.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isAutoManageEnabled())
            signinClient.stopAutoManage((AppCompatActivity) mActivity);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(signinClient != null && !signinClient.isConnected())
            signinClient.connect();


        /* da verificare questa chiamata */
        /*
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (optionalPendingResult.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(SocialConstants.TAG, "Got cached sign-in");
            GoogleSignInResult result = optionalPendingResult.get();
            handleSignInResult(result);
        }
         */
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = getActivity();
        initGoogleApi();
    }

    private void initGoogleApi() {
        GoogleApiClientConnectionListener googleApiClientConnectionListener = new GoogleApiClientConnectionListener(mActivity);
        googleSignInOptions = createGoogleSignInOptions();
        signinClient = createSigninClient(mActivity, googleApiClientConnectionListener);
    }

    private synchronized GoogleApiClient createSigninClient(Context context, GoogleApiClientConnectionListener listener) {
        if(isAutoManageEnabled()) {
            return new Builder(context)
                    .addConnectionCallbacks(listener)
                    .enableAutoManage((AppCompatActivity) context, listener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();
        } else {
            return new Builder(context)
                    .addConnectionCallbacks(listener)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                    .build();
        }
    }

    private GoogleSignInOptions createGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.google_layout, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Object btnSignIn;

        if (Button.class.isInstance(view.findViewById(R.id.buttonloginWithGoogle))) {
            btnSignIn = view.findViewById(R.id.buttonloginWithGoogle);
            ((Button) btnSignIn).setOnClickListener(this);

        } else if (ImageButton.class.isInstance(view.findViewById(R.id.buttonloginWithGoogle))) {
            btnSignIn = view.findViewById(R.id.buttonloginWithGoogle);
            ((ImageButton) btnSignIn).setOnClickListener(this);

        } else {
            btnSignIn = view.findViewById(R.id.buttonloginWithGoogle);
            ((SignInButton) btnSignIn).setOnClickListener(this);

        }
        Button btnSignOut = view.findViewById(R.id.sign_out_button);
        Button btnDisconnect = view.findViewById(R.id.disconnect_button);
        btnSignOut.setOnClickListener(this);
        btnDisconnect.setOnClickListener(this);

        return view;
    }


    //>>>>> SIGN IN
    public void signIn() {
        requestSignIn(signinClient);
    }

    private void requestSignIn(GoogleApiClient apiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        getActivity().startActivityForResult(signInIntent, SocialConstants.GOOGLE_API_CLIENT_SIGN_IN_REQUEST_CODE);
    }
    //**************************************************


    //>>>>> SIGN OUT
    public void signOut() {
        SignOutResultCallback callback = new SignOutResultCallback(mActivity);
        requestSignOut(signinClient, callback);
    }

    private void requestSignOut(GoogleApiClient apiClient, SignOutResultCallback callback) {
        Auth.GoogleSignInApi.signOut(apiClient).setResultCallback(callback);
    }
    //**************************************************


    //>>>>> REVOKE ACCESS
    public void revokeAccess() {
        RevokeAccessResultCallback callback = new RevokeAccessResultCallback(mActivity);
        requestRevokeAccess(signinClient, callback);
    }

    private void requestRevokeAccess(GoogleApiClient apiClient, RevokeAccessResultCallback callback) {
        Auth.GoogleSignInApi.revokeAccess(apiClient).setResultCallback(callback);
    }
    //**************************************************


    /* View.OnClickListener */
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.buttonloginWithGoogle) {
            signIn();

        } else if(id == R.id.sign_out_button) {
            signOut();

        } else if(id == R.id.disconnect_button) {
            revokeAccess();

        }
    }
    //***************************************************


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (SocialConstants.isDebug  && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.d(SocialConstants.TAG, "ON ACTIVITY RESULT");
            Log.d(SocialConstants.TAG, "****************** " + (RESULT_OK == resultCode ? "RESULT_OK" : "RESULT_CANCELED") + " ***********");
        }

        if (requestCode == SocialConstants.GOOGLE_API_CLIENT_CONNECT_REQUEST_CODE) {
            handleGmsConnectionResult(resultCode);

        } else if (requestCode == SocialConstants.GOOGLE_API_CLIENT_SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                handleSignInResult(data);
            }
        }
    }


    /* handle */
    private void handleSignInResult(Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleSignInResult(result);
    }

    private void handleGmsConnectionResult(int resultCode) {
        if (resultCode == RESULT_OK) {
            signinClient.connect();
        }
    }
    //**************************************************


    public boolean isAutoManageEnabled() {
        return isAutoManageEnabled;
    }

    public static void setAutoManageEnabled(boolean autoManageEnabled) {
        isAutoManageEnabled = autoManageEnabled;
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(SocialConstants.TAG, "handleSignInResult : " + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getActivity(), "GoogleSignInAccount result " + acct.getDisplayName(), Toast.LENGTH_SHORT).show();

            SocialUtils.getInstance().updateGoogleUserData(acct.getIdToken());

            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    public void handleSignOutResult(Status status) {
        updateUI(false);
    }

    public void handleRevokeAccessResult(Status status) {
        updateUI(false);
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            view.findViewById(R.id.buttonloginWithGoogle).setVisibility(View.GONE);
            view.findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);

        } else {
            view.findViewById(R.id.buttonloginWithGoogle).setVisibility(View.VISIBLE);
            view.findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }
}

package social_library.social.google.listener;

import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import armymau.it.social_library.R;
import social_library.utils.SocialConstants;

public class GoogleApiClientConnectionListener implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Context context;

    public GoogleApiClientConnectionListener(Context context) {
        this.context = context;
    }


    /* GoogleApiClient.ConnectionCallbacks */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(SocialConstants.TAG, "onConnected");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(SocialConstants.TAG, "onConnectionSuspended: " + cause);
    }
    //**************************************************


    /* GoogleApiClient.OnConnectionFailedListener */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(SocialConstants.TAG, "onConnectionFailed: " + connectionResult);

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult((AppCompatActivity) context, SocialConstants.GOOGLE_API_CLIENT_CONNECT_REQUEST_CODE);
            } catch (IntentSender.SendIntentException e) {
                Log.e(SocialConstants.TAG, "Unable to resolve connection issue with Smart Lock Google Api Client", e);
                Snackbar.make(((AppCompatActivity) context).findViewById(android.R.id.content), R.string.connection_error, Snackbar.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(((AppCompatActivity) context).findViewById(android.R.id.content), R.string.connection_error_response + connectionResult.getErrorCode(), Snackbar.LENGTH_LONG).show();
        }
    }
    //**************************************************
}

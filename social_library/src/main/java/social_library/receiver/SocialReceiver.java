package social_library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import armymau.it.social_library.R;
import social_library.view.SocialGenericDialog;

public class SocialReceiver extends BroadcastReceiver {

    public static boolean isNetworkAvailable;
    private Context context;
    private SocialGenericDialog dialog;

    public SocialReceiver(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        isNetworkAvailable = intent.getExtras().getBoolean("isNetworkAvailable");
        if (!isNetworkAvailable) {

            if (dialog == null) {
                dialog = new SocialGenericDialog(this.context, this.context.getResources().getString(R.string.no_connection_wrong_title), this.context.getResources().getString(R.string.no_connection_wrong_message), false);
            }
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                }
            });
            if (!dialog.isVisible() && !dialog.isAdded()) {
                dialog.show();
            }
        }
    }
}


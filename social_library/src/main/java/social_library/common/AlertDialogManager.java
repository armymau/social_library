package social_library.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import armymau.it.social_library.R;

public class AlertDialogManager {

    public void showAlertDialog(Context context, String title, String message, Boolean status) {

        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status != null)
            alertDialog.setButton(context.getResources().getString(R.string.generic_message_label), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        alertDialog.show();
    }
}

package social_library.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import armymau.it.social_library.R;

@SuppressLint("ValidFragment")
public class SocialGenericDialog extends DialogFragment {

    private static final String KEY_MSG = "KEY_MSG";
    private static final String KEY_EXIT = "KEY_EXIT";
    private final boolean wantToClose;
    public AlertDialog.Builder dialog;
    private Context context;
    private String title;
    private String message;
    private OnDismissListener mOnDismissListener;

    public SocialGenericDialog() {
        wantToClose = false;
    }

    public SocialGenericDialog(@NonNull Context context) {
        this.context = context;
        wantToClose = false;
    }

    public SocialGenericDialog(@NonNull Context context, String title, String message, boolean wantToClose) {
        this.context = context;
        this.title = title;
        this.message = message;
        this.wantToClose = wantToClose;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(context.getResources().getString(R.string.generic_message_label), getArguments().getBoolean(KEY_EXIT) ? new ExitOnClick() : null);
        setCancelable(false);
        builder.setCancelable(false);
        return builder.create();
    }

    public void show() {
        Bundle arguments = new Bundle(2);
        arguments.putString(KEY_MSG, message);
        arguments.putBoolean(KEY_EXIT, wantToClose);
        if(getArguments() != null) {
            getArguments().clear();
        }
        setArguments(arguments);
        try {
            show(((AppCompatActivity) context).getSupportFragmentManager(), SocialGenericDialog.class.getSimpleName());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null)
            mOnDismissListener.onDismiss(dialog);
    }

    public void setOnDismissListener(final OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    private final class ExitOnClick implements OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    }
}

package social_library.social.facebook.task;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import armymau.it.social_library.R;
import core.connection.model.GenericResponse;
import core.service.ServiceManager;
import core.utils.CoreConstants;
import social_library.common_interface.SocialServiceResponse;
import social_library.model.facebook.Cover;
import social_library.model.facebook.FacebookObject;
import social_library.model.facebook.Friends;
import social_library.model.facebook.Picture;
import social_library.model.facebook.Posts;
import social_library.receiver.SocialReceiver;
import social_library.utils.SocialConstants;
import social_library.utils.SocialUtils;
import social_library.view.SocialGenericDialog;

public class FacebookTask {

    private Context context;
    private SocialGenericDialog dialog;

    public FacebookTask(Context context) {
        this.context = context;
    }

    public void getProfileFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=about,birthday,email,hometown&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET PROFILE FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetProfileFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getPictureFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=picture.height(640).width(640)&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET PICTURE FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetPictureFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getCoverAccountFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=cover&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET COVER ACCOUNT FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetCoverAccountFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getPhotosFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me/photos&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET PHOTOS FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetPhotosFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getFriendsFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=friends&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET FRIENDS FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetFriendsFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getPostsFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=posts&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET POSTS FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetPostsFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getFullPostsFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=posts%7Bstory%2Ccreated_time%2Cdescription%2Cfull_picture%2Cmessage%2Cattachments%7D&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET FULL POSTS FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetFullPostsFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }

    public void getLikesFromMyFacebook() {

        String address = SocialConstants.FACEBOOK_BASE_URL + "/me?fields=likes&locale=";
        if (SocialConstants.isDebug && !SocialConstants.isSigned) {
            Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            Log.e(SocialConstants.TAG, "GET LIKES FROM MY FACEBOOK");
            Log.d(SocialConstants.TAG, "REMOTE REQUEST   : " + address);
        }
        new GetLikesFromMyFacebook(context, address + Locale.getDefault(), getAuthorization()).execute();
    }
    //*************************************************************************************



    /* ServiceManager */
    private class GetCoverAccountFromMyFacebook extends ServiceManager {
        public GetCoverAccountFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_COVER, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, Object>(), authorization, null, false, false, FacebookObject.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                Cover cover = ((FacebookObject) result).getCover();
                if (cover != null) {
                    if (cover.getSource() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_COVER, cover.getSource());
                    }
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetFriendsFromMyFacebook extends ServiceManager {
        public GetFriendsFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_FRIENDS, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, String>(), authorization, null, false, false, FacebookObject.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                Friends friends = ((FacebookObject) result).getFriends();
                if (friends != null) {
                    if (friends.getData() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_FRIENDS, friends);
                    }
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetFullPostsFromMyFacebook extends ServiceManager {
        public GetFullPostsFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_FULL_POSTS, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, String>(), authorization, null, false, false, FacebookObject.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                Posts status = ((FacebookObject) result).getPosts();
                if (status != null) {
                    ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_FULL_POSTS, ((FacebookObject) result).getPosts().getData());
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetLikesFromMyFacebook extends ServiceManager {
        public GetLikesFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_LIKES, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, String>(), authorization, null, false, false, GenericResponse.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                int status = ((GenericResponse) result).getStatus();
                if (status == 200) {
                    if (((GenericResponse) result).getData() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_LIKES, ((GenericResponse) result).getData());
                    }
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetPhotosFromMyFacebook extends ServiceManager {
        public GetPhotosFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_PHOTOS, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, String>(), authorization, null, false, false, GenericResponse.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                int status = ((GenericResponse) result).getStatus();
                if (status == 200) {
                    if (((GenericResponse) result).getData() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_PHOTOS, ((GenericResponse) result).getData());
                    }
                }
            }
        }
    }

    private class GetPictureFromMyFacebook extends ServiceManager {
        public GetPictureFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_PICTURE, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new HashMap<String, Object>(), authorization, null, false, false, FacebookObject.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                Picture picture = ((FacebookObject) result).getPicture();
                if (picture != null) {
                    if(picture.getData() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_PICTURE, picture.getData());
                    }
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetPostsFromMyFacebook extends ServiceManager {
        public GetPostsFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_POSTS, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new LinkedHashMap<String, String>(), authorization, null, false, false, GenericResponse.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                int status = ((GenericResponse) result).getStatus();
                if (status == 200) {
                    if (((GenericResponse) result).getData() != null) {
                        ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_POSTS, ((GenericResponse) result).getData());
                    }
                }
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }

    private class GetProfileFromMyFacebook extends ServiceManager {
        public GetProfileFromMyFacebook(Context context, String url, HashMap<String, String> authorization) {
            super(SocialConstants.FACEBOOK_METHOD_GET_PROFILE, CoreConstants.HTTP_METHOD_GET_WITH_AUTHORIZATION, (AppCompatActivity) context, url, new HashMap<String, Object>(), authorization, null, false, false, FacebookObject.class);
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);

            if (result != null) {
                if (SocialConstants.isDebug && !SocialConstants.isSigned) {
                    Log.d(SocialConstants.TAG, ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    Log.e(SocialConstants.TAG, "RESPONSE");
                    Log.e(SocialConstants.TAG, result.toString());
                }
                ((SocialServiceResponse) context).onPostProcessFinish(SocialConstants.FACEBOOK_METHOD_GET_PROFILE, result);
            } else if (SocialReceiver.isNetworkAvailable) {
                if (context != null)
                    genericError(context);
            }
        }
    }
    //*************************************************************************************



    /* private methods */
    private static HashMap<String, String> getAuthorization() {
        HashMap <String, String> result = new HashMap<>();
        result.put("authorization", "Bearer " + SocialUtils.getInstance().retrieveUserProfileData().getFacebook_userToken());
        return result;
    }

    private void genericError(final Context context) {

        if (dialog == null) {
            dialog = new SocialGenericDialog(context, context.getResources().getString(R.string.somethig_wrong_title), context.getResources().getString(R.string.somethig_wrong_message), false);
        }
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((AppCompatActivity) context).finish();
            }
        });
        if (!dialog.isVisible() && !dialog.isAdded()) {
            dialog.show();
        }
    }
    //***************************
}

package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Posts implements Parcelable {

    private ArrayList<DataPosts> data;
    private Paging paging;

    public Posts(ArrayList<DataPosts> data, Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    public Posts() {
        this.data = new ArrayList<>();
        this.paging = null;
    }

    public ArrayList<DataPosts> getData() {
        return data;
    }

    public void setData(ArrayList<DataPosts> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    protected Posts(Parcel in) {
        data = in.createTypedArrayList(DataPosts.CREATOR);
        paging = in.readParcelable(Paging.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
        dest.writeParcelable(paging, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Posts> CREATOR = new Creator<Posts>() {
        @Override
        public Posts createFromParcel(Parcel in) {
            return new Posts(in);
        }

        @Override
        public Posts[] newArray(int size) {
            return new Posts[size];
        }
    };
}

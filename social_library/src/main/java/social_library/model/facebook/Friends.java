package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Friends implements Parcelable {
    private ArrayList<DataFriends> data;
    private Summary summary;
    private PagingFriends paging;

    public Friends() {
        this.data = new ArrayList<>();
        this.summary = null;
        this.paging = null;
    }

    public Friends(ArrayList<DataFriends> data, Summary summary, PagingFriends paging) {
        this.data = data;
        this.summary = summary;
        this.paging = paging;
    }

    public ArrayList<DataFriends> getData() {
        return data;
    }

    public void setData(ArrayList<DataFriends> data) {
        this.data = data;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public PagingFriends getPaging() {
        return paging;
    }

    public void setPaging(PagingFriends paging) {
        this.paging = paging;
    }

    protected Friends(Parcel in) {
        data = in.createTypedArrayList(DataFriends.CREATOR);
        summary = in.readParcelable(Summary.class.getClassLoader());
        paging = in.readParcelable(PagingFriends.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
        dest.writeParcelable(summary, flags);
        dest.writeParcelable(paging, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Friends> CREATOR = new Creator<Friends>() {
        @Override
        public Friends createFromParcel(Parcel in) {
            return new Friends(in);
        }

        @Override
        public Friends[] newArray(int size) {
            return new Friends[size];
        }
    };
}

package social_library.model.facebook;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Attachments implements Parcelable {

    private ArrayList<DataAttachments> dataAttachmentses;

    public Attachments(ArrayList<DataAttachments> dataAttachmentses) {
        this.dataAttachmentses = dataAttachmentses;
    }

    public Attachments() {
        this.dataAttachmentses = new ArrayList<>();
    }

    public ArrayList<DataAttachments> getDataAttachmentses() {
        return dataAttachmentses;
    }

    public void setDataAttachmentses(ArrayList<DataAttachments> dataAttachmentses) {
        this.dataAttachmentses = dataAttachmentses;
    }

    protected Attachments(Parcel in) {
        dataAttachmentses = in.createTypedArrayList(DataAttachments.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(dataAttachmentses);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Attachments> CREATOR = new Creator<Attachments>() {
        @Override
        public Attachments createFromParcel(Parcel in) {
            return new Attachments(in);
        }

        @Override
        public Attachments[] newArray(int size) {
            return new Attachments[size];
        }
    };
}

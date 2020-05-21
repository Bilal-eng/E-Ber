package com.example.e_ber;

import android.os.Parcel;
import android.os.Parcelable;

public class MahallelerModeli implements Parcelable {
    private String mahalle_id,mahalle_title,mahalle_key,mahalle_ilcekey,mahalle_berber;

    public String getMahalle_berber() {
        return mahalle_berber;
    }

    public void setMahalle_berber(String mahalle_berber) {
        this.mahalle_berber = mahalle_berber;
    }

    public String getMahalle_id() {
        return mahalle_id;
    }

    public void setMahalle_id(String mahalle_id) {
        this.mahalle_id = mahalle_id;
    }

    public String getMahalle_title() {
        return mahalle_title;
    }

    public void setMahalle_title(String mahalle_title) {
        this.mahalle_title = mahalle_title;
    }

    public String getMahalle_key() {
        return mahalle_key;
    }

    public void setMahalle_key(String mahalle_key) {
        this.mahalle_key = mahalle_key;
    }

    public String getMahalle_ilcekey() {
        return mahalle_ilcekey;
    }

    public void setMahalle_ilcekey(String mahalle_ilcekey) {
        this.mahalle_ilcekey = mahalle_ilcekey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mahalle_id);
        dest.writeString(this.mahalle_title);
        dest.writeString(this.mahalle_key);
        dest.writeString(this.mahalle_ilcekey);
        dest.writeString(this.mahalle_berber);
    }

    public MahallelerModeli() {
    }

    protected MahallelerModeli(Parcel in) {
        this.mahalle_id = in.readString();
        this.mahalle_title = in.readString();
        this.mahalle_key = in.readString();
        this.mahalle_ilcekey = in.readString();
        this.mahalle_berber = in.readString();
    }

    public static final Parcelable.Creator<MahallelerModeli> CREATOR = new Parcelable.Creator<MahallelerModeli>() {
        @Override
        public MahallelerModeli createFromParcel(Parcel source) {
            return new MahallelerModeli(source);
        }

        @Override
        public MahallelerModeli[] newArray(int size) {
            return new MahallelerModeli[size];
        }
    };
}

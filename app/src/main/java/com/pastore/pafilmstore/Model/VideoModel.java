package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class VideoModel implements Parcelable {

    String key;
    String name;

    public VideoModel(String key, String name) {
        this.key = key;
        this.name = name;
    }

    protected VideoModel(Parcel in) {
        key = in.readString();
        name = in.readString();
    }

    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel in) {
            return new VideoModel(in);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(name);
    }
}

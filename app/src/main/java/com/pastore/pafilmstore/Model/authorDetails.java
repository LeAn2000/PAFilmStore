package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class authorDetails implements Parcelable {
    String username;
    String avatar_path;
    float rating;

    public authorDetails(String username, String avatar_path, float rating) {
        this.username = username;
        this.avatar_path = avatar_path;
        this.rating = rating;
    }

    protected authorDetails(Parcel in) {
        username = in.readString();
        avatar_path = in.readString();
        rating = in.readFloat();
    }

    public static final Creator<authorDetails> CREATOR = new Creator<authorDetails>() {
        @Override
        public authorDetails createFromParcel(Parcel in) {
            return new authorDetails(in);
        }

        @Override
        public authorDetails[] newArray(int size) {
            return new authorDetails[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(avatar_path);
        dest.writeFloat(rating);
    }
}

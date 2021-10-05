package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable {

    String content;

    @SerializedName("author_details")
    authorDetails author_details;

    public Review(String content, authorDetails author_details) {
        this.content = content;
        this.author_details = author_details;
    }

    protected Review(Parcel in) {
        content = in.readString();
        author_details = in.readParcelable(authorDetails.class.getClassLoader());
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getContent() {
        return content;
    }

    public authorDetails getAuthor_details() {
        return author_details;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeParcelable(author_details, flags);
    }
}

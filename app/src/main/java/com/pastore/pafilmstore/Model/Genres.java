package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Genres implements Parcelable {
    int id;
    String name;

    public Genres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Genres(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    public int getId() {
        return id;
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
        dest.writeInt(id);
        dest.writeString(name);
    }
}

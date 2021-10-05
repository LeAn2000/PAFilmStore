package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmModel implements Parcelable  {
    private String title;
    private String original_title;
    private String status;

    @SerializedName("tagline")
    private String tagline;

    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private int id;
    private float vote_average;
    private String overview;

    @SerializedName("genres")
    private final List<Genres> listGen;

    public FilmModel(String title, String original_title, String status, String tagline, String poster_path, String backdrop_path, String release_date, int id, float vote_average, String overview, List<Genres> listGen) {
        this.title = title;
        this.original_title = original_title;
        this.status = status;
        this.tagline = tagline;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_average = vote_average;
        this.overview = overview;
        this.listGen = listGen;
    }

    protected FilmModel(Parcel in) {
        title = in.readString();
        original_title = in.readString();
        status = in.readString();
        tagline = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        release_date = in.readString();
        id = in.readInt();
        vote_average = in.readFloat();
        overview = in.readString();
        listGen = in.createTypedArrayList(Genres.CREATOR);
    }

    public static final Creator<FilmModel> CREATOR = new Creator<FilmModel>() {
        @Override
        public FilmModel createFromParcel(Parcel in) {
            return new FilmModel(in);
        }

        @Override
        public FilmModel[] newArray(int size) {
            return new FilmModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public List<Genres> getListGen() {
        return listGen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(original_title);
        dest.writeString(status);
        dest.writeString(tagline);
        dest.writeString(poster_path);
        dest.writeString(backdrop_path);
        dest.writeString(release_date);
        dest.writeInt(id);
        dest.writeFloat(vote_average);
        dest.writeString(overview);
        dest.writeTypedList(listGen);
    }
}

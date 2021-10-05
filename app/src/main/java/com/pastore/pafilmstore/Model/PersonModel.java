package com.pastore.pafilmstore.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonModel implements Parcelable {

    private int gender;
    private int id;
    private String place_of_birth;
    private String birthday;
    private String biography;
    private String name;
    private String known_for_department;
    private String profile_path;

    @SerializedName("known_for")
    private List<FilmModel> listFilm;

    public PersonModel(int gender, int id, String place_of_birth, String birthday, String biography, String name, String known_for_department, String profile_path, List<FilmModel> listFilm) {
        this.gender = gender;
        this.id = id;
        this.place_of_birth = place_of_birth;
        this.birthday = birthday;
        this.biography = biography;
        this.name = name;
        this.known_for_department = known_for_department;
        this.profile_path = profile_path;
        this.listFilm = listFilm;
    }

    protected PersonModel(Parcel in) {
        gender = in.readInt();
        id = in.readInt();
        place_of_birth = in.readString();
        birthday = in.readString();
        biography = in.readString();
        name = in.readString();
        known_for_department = in.readString();
        profile_path = in.readString();
        listFilm = in.createTypedArrayList(FilmModel.CREATOR);
    }

    public static final Creator<PersonModel> CREATOR = new Creator<PersonModel>() {
        @Override
        public PersonModel createFromParcel(Parcel in) {
            return new PersonModel(in);
        }

        @Override
        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };

    public int getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBiography() {
        return biography;
    }

    public String getName() {
        return name;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public List<FilmModel> getListFilm() {
        return listFilm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gender);
        dest.writeInt(id);
        dest.writeString(place_of_birth);
        dest.writeString(birthday);
        dest.writeString(biography);
        dest.writeString(name);
        dest.writeString(known_for_department);
        dest.writeString(profile_path);
        dest.writeTypedList(listFilm);
    }
}

package com.pastore.pafilmstore.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pastore.pafilmstore.Model.PersonModel;

import java.util.List;

public class PersonReponse {
    @SerializedName("results")
    @Expose()
    private List<PersonModel> PersonList;

    public List<PersonModel> getPeson(){
        return PersonList;
    }


    @Override
    public String toString() {
        return "FilmSearchReponse{" +
                ", filmModelList=" + PersonList +
                '}';
    }
}

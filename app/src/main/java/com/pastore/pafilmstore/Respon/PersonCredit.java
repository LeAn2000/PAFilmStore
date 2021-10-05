package com.pastore.pafilmstore.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pastore.pafilmstore.Model.FilmModel;

import java.util.List;

public class PersonCredit {
    @SerializedName("total_results")
    @Expose()
    private int Total;

    @SerializedName("cast")
    @Expose()
    private List<FilmModel> filmModelList;


    public int Get_total(){
        return Total;
    }


    public List<FilmModel> getFilm(){
        return filmModelList;
    }


    @Override
    public String toString() {
        return "FilmSearchReponse{" +
                "Total=" + Total +
                ", filmModelList=" + filmModelList +
                '}';
    }
}

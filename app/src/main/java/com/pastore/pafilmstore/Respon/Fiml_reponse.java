package com.pastore.pafilmstore.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Model.Genres;

import java.util.List;

public class Fiml_reponse {
    private FilmModel filmModel;

    public FilmModel getFilmModel(){
        return filmModel;
    }

    @SerializedName("genres")
    @Expose()
    private List<Genres> GenresModel;

    public List<Genres> GetGenresModel(){return GenresModel;}

    @Override
    public String toString() {
        return "Fiml_reponse{" +
                "filmModel=" + filmModel +
                '}';
    }
}

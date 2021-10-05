package com.pastore.pafilmstore.Respontories;

import androidx.lifecycle.LiveData;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Request.Popular;

import java.util.List;

public class Popresponse {

    private static Popresponse instance;

    private Popular filmAPIclient;

    private String mquery;
    private int page;
    private int page_simi;
    private int id_film;


    public static Popresponse getInstance(){
        if(instance == null){
            instance = new Popresponse();
        }
        return instance;
    }

    private Popresponse(){
            filmAPIclient = Popular.getInstance();
    }

    public LiveData<List<FilmModel>> getFilm(){return filmAPIclient.Getmovies();}

    public void getPop(int page)
    {
        page_simi = page;
        filmAPIclient.GetTrending(page);
    }
    public void getnext(){
        getPop(page_simi+1);
    }





}

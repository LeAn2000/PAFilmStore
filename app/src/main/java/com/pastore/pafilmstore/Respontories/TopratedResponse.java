package com.pastore.pafilmstore.Respontories;

import androidx.lifecycle.LiveData;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Request.Toprated;

import java.util.List;

public class TopratedResponse {

    private static TopratedResponse instance;

    private Toprated filmAPIclient;
    private int page_simi;



    public static TopratedResponse getInstance(){
        if(instance == null){
            instance = new TopratedResponse();
        }
        return instance;
    }

    private TopratedResponse(){
            filmAPIclient = Toprated.getInstance();
    }

    public LiveData<List<FilmModel>> getFilm(){return filmAPIclient.Getmovies();}

    public void getTorated(int page)
    {
        page_simi = page;
        filmAPIclient.GetToprated(page);
    }
    public void getnext(){
        getTorated(page_simi+1);
    }





}

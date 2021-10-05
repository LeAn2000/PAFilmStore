package com.pastore.pafilmstore.Respontories;

import androidx.lifecycle.LiveData;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Request.Upcoming;

import java.util.List;

public class UpcomingResponse {

    private static UpcomingResponse instance;

    private Upcoming filmAPIclient;
    private int page_simi;



    public static UpcomingResponse getInstance(){
        if(instance == null){
            instance = new UpcomingResponse();
        }
        return instance;
    }

    private UpcomingResponse(){
            filmAPIclient = Upcoming.getInstance();
    }

    public LiveData<List<FilmModel>> getFilm(){return filmAPIclient.Getmovies();}

    public void getUpcoming(int page)
    {
        page_simi = page;
        filmAPIclient.Getupcoming(page);
    }
    public void getnext(){
        getUpcoming(page_simi+1);
    }





}

package com.pastore.pafilmstore.Respontories;

import androidx.lifecycle.LiveData;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Request.FilmAPIclient;

import java.util.List;

public class FilmRespontory {

    private static  FilmRespontory instance;

    private FilmAPIclient filmAPIclient;

    private String mquery;
    private int page;
    private int page_simi;
    private int id_film;


    public static FilmRespontory getInstance(){
        if(instance == null){
            instance = new FilmRespontory();
        }
        return instance;
    }

    private FilmRespontory(){
            filmAPIclient = FilmAPIclient.getInstance();
    }

    public LiveData<List<FilmModel>> getFilm(){return filmAPIclient.Getmovies();}


    public void searchFilmApi(String query, int pagenum)
    {
        mquery = query;
        page = pagenum;
        filmAPIclient.searchMovieApi(query,pagenum);
    }


    public void SearchnextPage(){
        searchFilmApi(mquery,page+1);
    }
    public void getTrending(int page)
    {
        page_simi = page;
        filmAPIclient.GetTrending(page);
    }
    public void getnext(){
        getTrending(page_simi+1);
    }

    public void getPopular(int page)
    {
        page_simi = page;
        filmAPIclient.GetTrending(page);
    }
    public void getnextPop(){
        getTrending(page_simi+1);
    }





}

package com.pastore.pafilmstore.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Respontories.FilmRespontory;

import java.util.List;

public class FilmListViewModel extends ViewModel {

    private FilmRespontory filmRespontory;
    public FilmListViewModel() {
        filmRespontory = FilmRespontory.getInstance();
    }
    public LiveData<List<FilmModel>> getFilm()
    {
        return filmRespontory.getFilm();
    }

    public void searchFilmApi(String query, int pagenum)
    {
        filmRespontory.searchFilmApi(query,pagenum);
    }

    public void SearchnextPage()
    {
        filmRespontory.SearchnextPage();
    }


    public void getTrending(int page){
        filmRespontory.getTrending(page);
    }

    public void getnext(){filmRespontory.getnext();}

}

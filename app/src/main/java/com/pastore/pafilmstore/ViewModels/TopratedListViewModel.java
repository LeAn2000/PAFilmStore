package com.pastore.pafilmstore.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Respontories.TopratedResponse;

import java.util.List;

public class TopratedListViewModel extends ViewModel {

    private TopratedResponse filmRespontory;
    public TopratedListViewModel() {
        filmRespontory = TopratedResponse.getInstance();
    }
    public LiveData<List<FilmModel>> getFilm()
    {
        return filmRespontory.getFilm();
    }
    public void getTop(int page){
        filmRespontory.getTorated(page);
    }
    public void getnext(){filmRespontory.getnext();}
}

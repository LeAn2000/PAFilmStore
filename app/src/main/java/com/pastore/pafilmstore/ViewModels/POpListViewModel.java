package com.pastore.pafilmstore.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Respontories.Popresponse;

import java.util.List;

public class POpListViewModel extends ViewModel {

    private Popresponse filmRespontory;
    public POpListViewModel() {
        filmRespontory = Popresponse.getInstance();
    }
    public LiveData<List<FilmModel>> getFilm()
    {
        return filmRespontory.getFilm();
    }
    public void getPOp(int page){
        filmRespontory.getPop(page);
    }
    public void getnext(){filmRespontory.getnext();}
}

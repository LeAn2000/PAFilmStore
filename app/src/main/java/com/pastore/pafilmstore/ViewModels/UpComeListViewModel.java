package com.pastore.pafilmstore.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Respontories.UpcomingResponse;

import java.util.List;

public class UpComeListViewModel extends ViewModel {

    private UpcomingResponse filmRespontory;
    public UpComeListViewModel() {
        filmRespontory = UpcomingResponse.getInstance();
    }
    public LiveData<List<FilmModel>> getFilm()
    {
        return filmRespontory.getFilm();
    }
    public void getUPcome(int page){
        filmRespontory.getUpcoming(page);
    }
    public void getnext(){filmRespontory.getnext();}
}

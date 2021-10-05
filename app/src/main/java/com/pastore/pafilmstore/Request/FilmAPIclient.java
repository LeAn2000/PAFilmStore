package com.pastore.pafilmstore.Request;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.pastore.pafilmstore.Appexcute;
import com.pastore.pafilmstore.KeyFile.KeyAPI;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Respon.FilmSearchReponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class FilmAPIclient {
    public static MutableLiveData<List<FilmModel>> mfilm ;

    private static FilmAPIclient instance;

    private RetriveFilRunnable retriveFilRunnable;
    private TrendingRunnable trendingRunnable;


    public static FilmAPIclient getInstance(){
        if(instance==null)
            instance = new FilmAPIclient();
        return instance;
    }

    private FilmAPIclient(){
        mfilm = new MutableLiveData<>();
    }

    public LiveData<List<FilmModel>> Getmovies(){
        return mfilm;
    }
    public void searchMovieApi(String query , int pagenum){

        if(retriveFilRunnable !=null)
        {
            retriveFilRunnable = null;
        }
        retriveFilRunnable = new RetriveFilRunnable(query,pagenum);
        final Future myHandler = Appexcute.getInstance().networkIO().submit(retriveFilRunnable);


        Appexcute.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        },7000, TimeUnit.MILLISECONDS);

     }
    public void GetTrending(int page){

        if(trendingRunnable !=null)
        {
            trendingRunnable = null;
        }
        trendingRunnable = new TrendingRunnable(page);
        final Future myHandler = Appexcute.getInstance().networkIO().submit(trendingRunnable);
        Appexcute.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MILLISECONDS);

    }

    //-----------------------------/
    private class RetriveFilRunnable implements Runnable {
        private String query;
        private int pagenum;
        boolean cancleRequest;

        public RetriveFilRunnable(String query, int pagenum) {
            this.query = query;
            this.pagenum = pagenum;
            cancleRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMovies(query, pagenum).execute();
                if (cancleRequest)
                    return;
                if (response.code() == 200) {
                    List<FilmModel> list = new ArrayList<>(((FilmSearchReponse)response.body()).getFilm());
                    if (pagenum == 1) {
                        mfilm.postValue(list);
                    }
                    else {
                        List<FilmModel> currentmovie = mfilm.getValue();
                        currentmovie.addAll(list);
                        mfilm.postValue(currentmovie);
                    }

                } else {
                    String err = response.errorBody().string();
                    Log.v("Tag", "ERROR " + err);
                    mfilm.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mfilm.postValue(null);
            }
            if(cancleRequest==false)
                return;


        }

        private Call<FilmSearchReponse> getMovies(String query, int pagenum) {
            return Service.getApi_film().searchfilm(KeyAPI.API_key, query, pagenum);
        }

        private void CancleRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancleRequest = true;

        }


    }
    //-----------------------------/
    private class TrendingRunnable implements Runnable {
        boolean cancleRequest;
        int pagenum;
        public TrendingRunnable(int pagenum) {
            this.pagenum = pagenum;
            cancleRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getTrendingFilm(pagenum).execute();
                if (cancleRequest)
                    return;
                if (response.code() == 200) {
                    List<FilmModel> list = new ArrayList<>(((FilmSearchReponse)response.body()).getFilm());
                    if (pagenum == 1) {
                        mfilm.postValue(list);
                    }
                    else {
                        List<FilmModel> currentmovie = mfilm.getValue();
                        currentmovie.addAll(list);
                        mfilm.postValue(currentmovie);
                    }

                } else {
                    String err = response.errorBody().string();
                    Log.v("Tag", "ERROR " + err);
                    mfilm.postValue(null);
                }


            } catch (IOException e) {
                e.printStackTrace();
                mfilm.postValue(null);
            }
            if(cancleRequest==false)
                return;
        }

        private Call<FilmSearchReponse> getTrendingFilm(int pagenum) {
            return Service.getApi_film().Trendding(KeyAPI.API_key,pagenum);
        }

        private void CancleRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancleRequest = true;

        }


    }
    //-----------------------------/



}



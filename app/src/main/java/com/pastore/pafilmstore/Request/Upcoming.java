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

public class Upcoming {
    public static MutableLiveData<List<FilmModel>> mfilm ;

    private static Upcoming instance;

   ;
    private UpcomingRunnable upcomingRunnable;


    public static Upcoming getInstance(){
        if(instance==null)
            instance = new Upcoming();
        return instance;
    }

    private Upcoming(){
        mfilm = new MutableLiveData<>();
    }

    public LiveData<List<FilmModel>> Getmovies(){
        return mfilm;
    }

    public void Getupcoming(int page){

        if(upcomingRunnable !=null)
        {
            upcomingRunnable = null;
        }
        upcomingRunnable = new UpcomingRunnable(page);
        final Future myHandler = Appexcute.getInstance().networkIO().submit(upcomingRunnable);
        Appexcute.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MILLISECONDS);

    }





    //-----------------------------/
    private class UpcomingRunnable implements Runnable {
        boolean cancleRequest;
        int pagenum;
        public UpcomingRunnable(int pagenum) {
            this.pagenum = pagenum;
            cancleRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getUpcoming(pagenum).execute();
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

        private Call<FilmSearchReponse> getUpcoming(int pagenum) {
            return Service.getApi_film().Upcoming(KeyAPI.API_key,pagenum);
        }

        private void CancleRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancleRequest = true;

        }


    }




}



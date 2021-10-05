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

public class Popular {
    public static MutableLiveData<List<FilmModel>> mfilm ;

    private static Popular instance;

   ;
    private PopularRunnable popularRunnable;


    public static Popular getInstance(){
        if(instance==null)
            instance = new Popular();
        return instance;
    }

    private Popular(){
        mfilm = new MutableLiveData<>();
    }

    public LiveData<List<FilmModel>> Getmovies(){
        return mfilm;
    }

    public void GetTrending(int page){

        if(popularRunnable !=null)
        {
            popularRunnable = null;
        }
        popularRunnable = new PopularRunnable(page);
        final Future myHandler = Appexcute.getInstance().networkIO().submit(popularRunnable);
        Appexcute.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        },5000, TimeUnit.MILLISECONDS);

    }





    //-----------------------------/
    private class PopularRunnable implements Runnable {
        boolean cancleRequest;
        int pagenum;
        public PopularRunnable(int pagenum) {
            this.pagenum = pagenum;
            cancleRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getPopular(pagenum).execute();
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

        private Call<FilmSearchReponse> getPopular(int pagenum) {
            return Service.getApi_film().Popular(KeyAPI.API_key,pagenum);
        }

        private void CancleRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancleRequest = true;

        }


    }




}



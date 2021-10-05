package com.pastore.pafilmstore.Request;

import com.pastore.pafilmstore.KeyFile.Film_Api;
import com.pastore.pafilmstore.KeyFile.KeyAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Retrofit.Builder retroBuilder = new Retrofit.Builder()
            .baseUrl(KeyAPI.Url_base)
            .addConverterFactory(GsonConverterFactory.create());
    private static  Retrofit retrofit   = retroBuilder.build();
    private static Film_Api api_film = retrofit.create(Film_Api.class);
    public static Film_Api getApi_film(){
        return api_film;
    }
}

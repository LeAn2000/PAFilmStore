package com.pastore.pafilmstore.KeyFile;

import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Model.PersonModel;
import com.pastore.pafilmstore.Respon.FilmSearchReponse;
import com.pastore.pafilmstore.Respon.PersonCredit;
import com.pastore.pafilmstore.Respon.PersonReponse;
import com.pastore.pafilmstore.Respon.ReviewReponse;
import com.pastore.pafilmstore.Respon.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Film_Api {
    @GET("/3/search/movie")
    Call<FilmSearchReponse> searchfilm(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page);

    @GET("/3/trending/movie/day?")
    Call<FilmSearchReponse> Trendding(
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/trending/person/week?")
    Call<PersonReponse> TrenddingPerson(
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/person/{person_id}?")
    Call<PersonModel> TrenddingPersonDetail(
            @Path("person_id") int person_id,
            @Query("api_key") String key
            );

    @GET("/3/person/{person_id}/movie_credits")
    Call<PersonCredit> TrenddingPersonCredit(
            @Path("person_id") int person_id,
            @Query("api_key") String key
    );

    @GET("/3/movie/{movie_id}/reviews")
    Call<ReviewReponse> Film_Review(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key
    );

    @GET("/3/trending/movie/week?")
    Call<FilmSearchReponse> Trenddingaweek(
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/movie/upcoming")
    Call<FilmSearchReponse> Upcoming(
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<FilmSearchReponse> TopRate(
            @Query("api_key") String key,
            @Query("page") int page);
    @GET("/3/movie/popular")
    Call<FilmSearchReponse> Popular(
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/movie/{movie_id}")
    Call<FilmModel> getMovie(
        @Path("movie_id") int movie_id,
        @Query("api_key") String api_key);

    @GET("/3/movie/{movie_id}/similar")
    Call<FilmSearchReponse> SimilarFilm(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/movie/{movie_id}/recommendations")
    Call<FilmSearchReponse> RecommendFilm(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key,
            @Query("page") int page);

    @GET("/3/movie/{movie_id}/videos")
    Call<VideoResponse> GetVideo(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key);

}

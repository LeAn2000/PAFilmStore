package com.pastore.pafilmstore.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pastore.pafilmstore.Model.Review;

import java.util.List;

public class ReviewReponse {
    @SerializedName("total_results")
    @Expose()
    private int Total;

    @SerializedName("results")
    @Expose()
    private List<Review> filmModelList;


    public int Get_total(){
        return Total;
    }


    public List<Review> getReview(){
        return filmModelList;
    }


    @Override
    public String toString() {
        return "FilmSearchReponse{" +
                "Total=" + Total +
                ", filmModelList=" + filmModelList +
                '}';
    }
}

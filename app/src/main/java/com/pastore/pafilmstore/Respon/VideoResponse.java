package com.pastore.pafilmstore.Respon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.pastore.pafilmstore.Model.VideoModel;

import java.util.List;

public class VideoResponse {

    @SerializedName("results")
    @Expose()
    private List<VideoModel> videoModelList;

    public List<VideoModel> getFilm(){
        return videoModelList;
    }

    @Override
    public String toString() {
        return "VideoResponse{" +
                "videoModelList=" + videoModelList +
                '}';
    }
}

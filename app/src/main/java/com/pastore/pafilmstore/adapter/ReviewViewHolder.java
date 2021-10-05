package com.pastore.pafilmstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pastore.pafilmstore.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

     TextView content,username;
     ImageView img;
     RatingBar ratingBar;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);

        content = itemView.findViewById(R.id.content_review);
        username = itemView.findViewById(R.id.username);

        img = itemView.findViewById(R.id.image_review);
        ratingBar = itemView.findViewById(R.id.rating_review);
    }


}

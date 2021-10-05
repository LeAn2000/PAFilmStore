package com.pastore.pafilmstore.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pastore.pafilmstore.R;

public class FilmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

     TextView title,realeas,id, vote_average;
     ImageView img;
     RatingBar ratingBar;

     OnListener onListener;
    public FilmViewHolder(@NonNull View itemView,OnListener onListener) {
        super(itemView);
        this.onListener = onListener;
        title = itemView.findViewById(R.id.txt_title);
        realeas = itemView.findViewById(R.id.txt_reles);
        vote_average = itemView.findViewById(R.id.vote_average);
        img = itemView.findViewById(R.id.image);
        ratingBar = itemView.findViewById(R.id.rating);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onListener.OnFilmClick(getAdapterPosition());
    }
}

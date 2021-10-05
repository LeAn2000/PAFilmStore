package com.pastore.pafilmstore.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pastore.pafilmstore.R;

public class FilmViewHolder_recom extends RecyclerView.ViewHolder implements View.OnClickListener {
     ImageView  img_recom;


     OnListener onListener;
    public FilmViewHolder_recom(@NonNull View itemView, OnListener onListener) {
        super(itemView);
        this.onListener = onListener;
        img_recom = itemView.findViewById(R.id.image_recom);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onListener.OnFilmClick(getAdapterPosition());
    }
}

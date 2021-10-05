package com.pastore.pafilmstore.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pastore.pafilmstore.R;

public class FilmViewHolder_simi extends RecyclerView.ViewHolder implements View.OnClickListener {
     ImageView  img_simi;


     OnListener onListener;
    public FilmViewHolder_simi(@NonNull View itemView, OnListener onListener) {
        super(itemView);
        this.onListener = onListener;
        img_simi = itemView.findViewById(R.id.image_simi);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onListener.OnRecomdClick(getAdapterPosition());
    }
}

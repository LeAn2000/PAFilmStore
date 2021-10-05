package com.pastore.pafilmstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pastore.pafilmstore.Model.Review;
import com.pastore.pafilmstore.R;

import java.util.List;

public class ReviewRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Review> filmModelList;


    public ReviewRecycler() {

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
       return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ReviewViewHolder)holder).username.setText(filmModelList.get(position).getAuthor_details().getUsername());
        ((ReviewViewHolder)holder).content.setText("Content: "+filmModelList.get(position).getContent());
        ((ReviewViewHolder)holder).ratingBar.setRating((filmModelList.get(position).getAuthor_details().getRating())/2);
        if(filmModelList.get(position).getAuthor_details().getAvatar_path() == null||
                filmModelList.get(position).getAuthor_details().getAvatar_path().isEmpty()||
                filmModelList.get(position).getAuthor_details().getAvatar_path()==" ")
        {
            Glide.with(holder.itemView.getContext()).load("https://secure.gravatar.com/avatar").into((((ReviewViewHolder)holder).img));
        }

        else
        {
            char a = filmModelList.get(position).getAuthor_details().getAvatar_path().substring(1).charAt(0);
            if(a == 'h'){
                Glide.with(holder.itemView.getContext()).load(filmModelList.get(position).getAuthor_details().getAvatar_path().substring(1)).into((((ReviewViewHolder)holder).img));
            }
            else
            {
                Glide.with(holder.itemView.getContext()).load("https://secure.gravatar.com/avatar/"+filmModelList.get(position).getAuthor_details().getAvatar_path().substring(1)).into((((ReviewViewHolder)holder).img));

            }
        }

    }

    @Override
    public int getItemCount() {
        if(filmModelList!=null)
            return filmModelList.size();
        return 0;
    }

    public void setFilmModelList(List<Review> filmModelList) {
        this.filmModelList = filmModelList;
        notifyDataSetChanged();
    }
}


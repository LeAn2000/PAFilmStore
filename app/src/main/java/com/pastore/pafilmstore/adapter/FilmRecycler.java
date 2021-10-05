package com.pastore.pafilmstore.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.R;

import java.util.List;

public class FilmRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<FilmModel> filmModelList;
    private OnListener onListener;

    public FilmRecycler(OnListener onListener) {
        this.onListener = onListener;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_list_item,parent,false);
       return new FilmViewHolder(view,onListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((FilmViewHolder)holder).title.setText(filmModelList.get(position).getTitle());
        ((FilmViewHolder)holder).realeas.setText("Release Date: "+filmModelList.get(position).getRelease_date());
        ((FilmViewHolder)holder).vote_average.setText("Vote Average: "+filmModelList.get(position).getVote_average());
        ((FilmViewHolder)holder).ratingBar.setRating((filmModelList.get(position).getVote_average())/2);



        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/"+filmModelList.get(position).getPoster_path()).into((((FilmViewHolder)holder).img));
    }

    @Override
    public int getItemCount() {
        if(filmModelList!=null)
            return filmModelList.size();
        return 0;
    }

    public void setFilmModelList(List<FilmModel> filmModelList) {
        this.filmModelList = filmModelList;
        notifyDataSetChanged();
    }

    public FilmModel getSelectedFilm(int pos){
        if(filmModelList!=null)
        {
            if(filmModelList.size()>0)
            {
                return filmModelList.get(pos);
            }
        }
        return null;

    }
}


package com.pastore.pafilmstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.pastore.pafilmstore.DetailsActivity;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.R;

import java.util.List;

public class SlideAdapter extends PagerAdapter {
    private Context context;
    private List<FilmModel> filmModelList;


    public SlideAdapter(Context context, List<FilmModel> filmModelList) {
        this.context = context;
        this.filmModelList = filmModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView img = view.findViewById(R.id.img_photo);
        RatingBar ratingBar = view.findViewById(R.id.rating_home);
        TextView title = view.findViewById(R.id.title_home);
        FilmModel film = filmModelList.get(position);
        if(film!=null)
        {
            title.setText(film.getTitle());
            ratingBar.setRating(film.getVote_average()/2);
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+film.getBackdrop_path()).into(img);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("film",filmModelList.get(position));
                ContextCompat.startActivity(context,intent,null);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(filmModelList!=null)
            return filmModelList.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

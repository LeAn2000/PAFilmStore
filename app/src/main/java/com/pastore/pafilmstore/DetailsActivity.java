package com.pastore.pafilmstore;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;
import com.pastore.pafilmstore.KeyFile.Film_Api;
import com.pastore.pafilmstore.KeyFile.KeyAPI;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Model.Genres;
import com.pastore.pafilmstore.Model.Review;
import com.pastore.pafilmstore.Model.VideoModel;
import com.pastore.pafilmstore.Request.Service;
import com.pastore.pafilmstore.Respon.FilmSearchReponse;
import com.pastore.pafilmstore.Respon.ReviewReponse;
import com.pastore.pafilmstore.Respon.VideoResponse;
import com.pastore.pafilmstore.adapter.FilmRecycler_recom;
import com.pastore.pafilmstore.adapter.FilmRecycler_simi;
import com.pastore.pafilmstore.adapter.NetWordChangeListener;
import com.pastore.pafilmstore.adapter.OnListener;
import com.pastore.pafilmstore.adapter.ReviewRecycler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsActivity extends AppCompatActivity implements OnListener {
    ImageView Img;
    TextView title,status,tagline,overview,txt,title_toolbar, genre;
    FilmModel filmModel;
    YouTubePlayerSupportFragmentX mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener moListener;
    Button btn, btnback;

    Toolbar toolbar;

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView_recom;
    private RecyclerView recyclerView_review;
    private FilmRecycler_simi filmRecycler1;
    private FilmRecycler_recom filmRecycler_recom;
    private ReviewRecycler reviewRecycler;

    NetWordChangeListener netWordChangeListener = new NetWordChangeListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Img = findViewById(R.id.Detail_image);
        title = findViewById(R.id.title_details);
        genre = findViewById(R.id.genre_details);
        status = findViewById(R.id.status_details);
        tagline = findViewById(R.id.tagline_details);
        overview = findViewById(R.id.overview_details);
        btn = findViewById(R.id.btnplay);
        btn.setForeground((getDrawable(R.drawable.ic_baseline_play_circle_filled_24)));
        btn.setBackground(null);
        title_toolbar = findViewById(R.id.title_toolbar);
        btnback = findViewById(R.id.btnback);
        btnback.setForeground((getDrawable(R.drawable.ic_baseline_arrow_back_ios_24)));
        btnback.setBackground(null);
        txt = findViewById(R.id.text1);
        mYouTubePlayerView = (YouTubePlayerSupportFragmentX)getSupportFragmentManager().findFragmentById(R.id.YTlayer);

        recyclerView1 = findViewById(R.id.recyclerView_simi);
        recyclerView_recom = findViewById(R.id.recyclerView_recom);
        recyclerView_review = findViewById(R.id.recyclerView_review);
        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        GetVideoID();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        ConfigRecyclerView();
        GetSimilar_Film();


    }
    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWordChangeListener, filter);
        super.onStart();
        GetretrofitAccordingtoID();

        ConfigRecyclerView_recom();
        GetRecom_Film();
        ConfigRecyclerView_review();
        GetReview();


    }
    @Override
    protected void onStop() {
        unregisterReceiver(netWordChangeListener);
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        GetretrofitAccordingtoID();
    }

    private FilmModel GetdatafromIntent() {
        if(getIntent().hasExtra("film")){
            FilmModel filmid  = getIntent().getParcelableExtra("film");

            return filmid;
        }
        else if(getIntent().hasExtra("film_similar")){
            FilmModel filmid  = getIntent().getParcelableExtra("film_similar");
            return filmid;
        }
        else if(getIntent().hasExtra("film_recomd")){
            FilmModel filmid  = getIntent().getParcelableExtra("film_recomd");
            return filmid;
        }
        return null;
    }

    private String ShowGenres(List<Genres> list) {

        if(list.size()>0 )
        {
            String a = "Genres: ";
            List<Genres> genresList = list;
            a = a+ genresList.get(0).getName();
            for(int i = 1; i<genresList.size();i++)
            {
                a = a + ", "+genresList.get(i).getName();
            }
            return a;
        }
        else
            return "Genres: ";
    }
    private void GetretrofitAccordingtoID(){
        Film_Api film_api = Service.getApi_film();
        Call<FilmModel> rescall = film_api.getMovie(GetdatafromIntent().getId(), KeyAPI.API_key);
        rescall.enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if(response.isSuccessful())
                {
                    filmModel = response.body();

                    if(filmModel!=null)
                    {


                        genre.setText(ShowGenres(filmModel.getListGen()));
                        title.setText(filmModel.getTitle());
                        title_toolbar.setText(filmModel.getTitle());
                        status.setText("Status : "+filmModel.getStatus());
                        tagline.setText("Tagline: "+filmModel.getTagline());
                        overview.setText("Overview: "+filmModel.getOverview());
                        Glide.with(DetailsActivity.this).load("https://image.tmdb.org/t/p/w500/"+filmModel.getPoster_path()).into(Img);
                    }
                }
                else
                {
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {

            }
        });
    }
    private void GetVideoID() {
        Film_Api film_api = Service.getApi_film();
        Call<VideoResponse> reponseCall = film_api.GetVideo(GetdatafromIntent().getId(),
                KeyAPI.API_key);
        reponseCall.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
                if(response.isSuccessful())
                {

                    List<VideoModel> vdfilmModels = new ArrayList<>(response.body().getFilm());
                    moListener = new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            if(vdfilmModels!=null)
                                youTubePlayer.loadVideo(vdfilmModels.get(0).getKey());
                            else
                                return;
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    };
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mYouTubePlayerView.initialize(KeyAPI.API_key_YT, moListener);
                            btn.setVisibility(View.INVISIBLE);
                            txt.setVisibility(View.INVISIBLE);

                        }
                    });
                }
                else
                {
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
    }


    private void ConfigRecyclerView(){
        filmRecycler1 = new FilmRecycler_simi(this);
        recyclerView1.setAdapter(filmRecycler1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void GetSimilar_Film() {
        Film_Api film_api = Service.getApi_film();
        Call<FilmSearchReponse> reponseCall = film_api.SimilarFilm(GetdatafromIntent().getId(),
                KeyAPI.API_key,1);


        reponseCall.enqueue(new Callback<FilmSearchReponse>() {
            @Override
            public void onResponse(Call<FilmSearchReponse> call, Response<FilmSearchReponse> response) {
                if(response.isSuccessful())
                {

                    List<FilmModel> models = new ArrayList<>(response.body().getFilm());
                    if(models!=null) filmRecycler1.setFilmModelList_simi(models);
                    else
                        filmRecycler1.setFilmModelList_simi(null);

                }
                else
                {
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<FilmSearchReponse> call, Throwable t) {

            }
        });
    }

    private void ConfigRecyclerView_recom(){
        filmRecycler_recom = new FilmRecycler_recom(this);

        recyclerView_recom.setAdapter(filmRecycler_recom);
        recyclerView_recom.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
    private void GetRecom_Film() {
        Film_Api film_api = Service.getApi_film();
        Call<FilmSearchReponse> reponseCall = film_api.RecommendFilm(GetdatafromIntent().getId(),
                KeyAPI.API_key,1);


        reponseCall.enqueue(new Callback<FilmSearchReponse>() {
            @Override
            public void onResponse(Call<FilmSearchReponse> call, Response<FilmSearchReponse> response) {
                if(response.isSuccessful())
                {

                    List<FilmModel> models = new ArrayList<>(response.body().getFilm());
                    if(models!=null) filmRecycler_recom.setFilmModelList_recom(models);
                    else
                        filmRecycler_recom.setFilmModelList_recom(null);

                }
                else
                {
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<FilmSearchReponse> call, Throwable t) {

            }
        });
    }


    private void ConfigRecyclerView_review(){
        reviewRecycler = new ReviewRecycler();

        recyclerView_review.setAdapter(reviewRecycler);
        recyclerView_review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void GetReview() {
        Film_Api film_api = Service.getApi_film();
        Call<ReviewReponse> reponseCall = film_api.Film_Review(GetdatafromIntent().getId(),
                KeyAPI.API_key);
        reponseCall.enqueue(new Callback<ReviewReponse>() {
            @Override
            public void onResponse(Call<ReviewReponse> call, Response<ReviewReponse> response) {
                if(response.isSuccessful())
                {

                    List<Review> models = new ArrayList<>(response.body().getReview());
                    if(models!=null) reviewRecycler.setFilmModelList(models);
                    else
                        reviewRecycler.setFilmModelList(null);

                }
                else
                {
                    try{
                        Log.v("Tag","Error"+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ReviewReponse> call, Throwable t) {

            }
        });
    }







    @Override
    public void OnFilmClick(int pos) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("film_recomd",filmRecycler_recom.getSelectedFilm_recomd(pos));
        startActivity(intent);
    }

    @Override
    public void OnRecomdClick(int pos) {

        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("film_similar",filmRecycler1.getSelectedFilm(pos));
        startActivity(intent);

    }

    @Override
    public void OnCatagoryClick(String cata) {

    }
}
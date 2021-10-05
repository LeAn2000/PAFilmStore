package com.pastore.pafilmstore;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import com.pastore.pafilmstore.KeyFile.Film_Api;
import com.pastore.pafilmstore.KeyFile.KeyAPI;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Model.PersonModel;
import com.pastore.pafilmstore.Request.Service;
import com.pastore.pafilmstore.Respon.PersonCredit;
import com.pastore.pafilmstore.adapter.FilmRecycler_simi;
import com.pastore.pafilmstore.adapter.OnListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Person_Activity extends AppCompatActivity implements OnListener {
    TextView Name,birthday,gender,Knowndepart,placebirth,overview,txttoolbar;
    ImageView img;
    Toolbar toolbar;
    Button btn;
    private RecyclerView recyclerView1;
    private FilmRecycler_simi filmRecycler1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__person_);
        Name = findViewById(R.id.Name_detail);
        birthday = findViewById(R.id.birthday);
        gender = findViewById(R.id.gender_detail);
        Knowndepart = findViewById(R.id.known_for_department_detail);
        placebirth = findViewById(R.id.place_of_birth);
        overview = findViewById(R.id.overview_details_person);
        img = findViewById(R.id.Detail_image_person);
        toolbar = findViewById(R.id.toolbar2);
        this.setSupportActionBar(toolbar);
        txttoolbar = findViewById(R.id.title_toolbar2);
        btn = findViewById(R.id.btnback_per);
        btn.setForeground((getDrawable(R.drawable.ic_baseline_arrow_back_ios_24)));
        btn.setBackground(null);
        recyclerView1 = findViewById(R.id.recyclerView_person);
        overview.setMovementMethod(new ScrollingMovementMethod());
        ConfigRecyclerView();
        GetPersonCredit();
        GetDetails();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }


    private PersonModel GetdatafromIntent() {
        if(getIntent().hasExtra("film")){
            PersonModel filmid  = getIntent().getParcelableExtra("film");
           return filmid;
        }
        return null;
    }

    private List<FilmModel> GetdatafromIntent_lstfilm() {
        if(getIntent().hasExtra("film")){
            PersonModel filmid  = getIntent().getParcelableExtra("film");
            List<FilmModel> lst = filmid.getListFilm();
            return lst;
        }
        return null;
    }


    private void GetDetails() {
        Film_Api film_api = Service.getApi_film();
        Call<PersonModel> reponseCall = film_api.TrenddingPersonDetail(GetdatafromIntent().getId(),
                KeyAPI.API_key);
        reponseCall.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                if(response.isSuccessful())
                {
                    PersonModel vdfilmModels = response.body();
                    Name.setText(vdfilmModels.getName());
                    txttoolbar.setText(vdfilmModels.getName());
                    birthday.setText("Birth day: "+vdfilmModels.getBirthday());
                    if(vdfilmModels.getGender()==1)
                        gender.setText("Gender: Women");
                    else
                        gender.setText("Gender: Man");
                    Knowndepart.setText("Known for department: "+vdfilmModels.getKnown_for_department());
                    placebirth.setText("Place of birth: "+vdfilmModels.getPlace_of_birth());
                    overview.setText("Biography: "+vdfilmModels.getBiography());
                    Glide.with(Detail_Person_Activity.this).load("https://image.tmdb.org/t/p/w500/"+vdfilmModels.getProfile_path()).into(img);

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
            public void onFailure(Call<PersonModel> call, Throwable t) {

            }
        });
    }

    private void GetPersonCredit() {
        Film_Api film_api = Service.getApi_film();
        Call<PersonCredit> reponseCall = film_api.TrenddingPersonCredit(GetdatafromIntent().getId(),
                KeyAPI.API_key);
        reponseCall.enqueue(new Callback<PersonCredit>() {
            @Override
            public void onResponse(Call<PersonCredit> call, Response<PersonCredit> response) {
                if(response.isSuccessful())
                {
                    List<FilmModel> vdfilmModels = new ArrayList<>(response.body().getFilm());
                    if(vdfilmModels!=null) filmRecycler1.setFilmModelList_simi(vdfilmModels);
                    else filmRecycler1.setFilmModelList_simi(null);
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
            public void onFailure(Call<PersonCredit> call, Throwable t) {

            }
        });
    }

    private void ConfigRecyclerView(){
        filmRecycler1 = new FilmRecycler_simi(this);
        recyclerView1.setAdapter(filmRecycler1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }


    @Override
    public void OnFilmClick(int pos) {


    }

    @Override
    public void OnRecomdClick(int pos) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("film",filmRecycler1.getSelectedFilm(pos));
        startActivity(intent);

    }

    @Override
    public void OnCatagoryClick(String cata) {

    }
}
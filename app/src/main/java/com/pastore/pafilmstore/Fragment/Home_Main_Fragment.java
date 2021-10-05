package com.pastore.pafilmstore.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pastore.pafilmstore.KeyFile.Film_Api;
import com.pastore.pafilmstore.KeyFile.KeyAPI;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.Model.PersonModel;
import com.pastore.pafilmstore.R;
import com.pastore.pafilmstore.Request.Service;
import com.pastore.pafilmstore.Respon.FilmSearchReponse;
import com.pastore.pafilmstore.Respon.PersonReponse;
import com.pastore.pafilmstore.adapter.SlideAdapter;
import com.pastore.pafilmstore.adapter.SlideAdapterPerson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Main_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Main_Fragment extends Fragment {
    private ViewPager viewPager, viewPagerweek, viewPagerperson;
    private CircleIndicator circleIndicator, circleIndicator_week,circleIndicator_person;
    private SlideAdapter slideAdapter;
    private SlideAdapterPerson slideAdapterPerson;
    private View mview;
    private Timer mtime;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Main_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Main_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Main_Fragment newInstance(String param1, String param2) {
        Home_Main_Fragment fragment = new Home_Main_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mview = inflater.inflate(R.layout.fragment_home__main_, container, false);
        viewPager = mview.findViewById(R.id.view_pager_slide);
        viewPagerweek = mview.findViewById(R.id.view_pager_slide_week);
        circleIndicator = mview.findViewById(R.id.circle_idicator);
        circleIndicator_week = mview.findViewById(R.id.circle_idicator_week);
        viewPagerperson = mview.findViewById(R.id.view_pager_slide_actor);
        circleIndicator_person = mview.findViewById(R.id.circle_idicator_actor);




        return mview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CreateSlide();
        CreateSlide_week();
        CreateSlide_person();
    }
    private void CreateSlide() {
        Film_Api film_api = Service.getApi_film();
        Call<FilmSearchReponse> reponseCall = film_api.Trendding(
                KeyAPI.API_key,1);
        reponseCall.enqueue(new Callback<FilmSearchReponse>() {
            @Override
            public void onResponse(Call<FilmSearchReponse> call, Response<FilmSearchReponse> response) {
                if(response.isSuccessful())
                {
                    List<FilmModel> vdfilmModels = new ArrayList<>(response.body().getFilm());
                    slideAdapter = new SlideAdapter(getActivity(),vdfilmModels);
                    viewPager.setAdapter(slideAdapter);
                    circleIndicator.setViewPager(viewPager);
                    slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

                    if(vdfilmModels==null||vdfilmModels.isEmpty()||viewPager==null)
                        return;
                    if(mtime ==null)
                        mtime = new Timer();

                    mtime.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    int current = viewPager.getCurrentItem();
                                    int total = vdfilmModels.size() -1;
                                    if(current<total){
                                        current++;
                                        viewPager.setCurrentItem(current);
                                    }
                                    else{
                                        viewPager.setCurrentItem(0);
                                    }
                                }
                            });
                        }
                    },500,3000);

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

    private void CreateSlide_week() {
        Film_Api film_api = Service.getApi_film();
        Call<FilmSearchReponse> reponseCall = film_api.Trenddingaweek(
                KeyAPI.API_key,1);
        reponseCall.enqueue(new Callback<FilmSearchReponse>() {
            @Override
            public void onResponse(Call<FilmSearchReponse> call, Response<FilmSearchReponse> response) {
                if(response.isSuccessful())
                {
                    List<FilmModel> vdfilmModels = new ArrayList<>(response.body().getFilm());
                    for(int i = 0; i<vdfilmModels.size();i++)
                    {
                        Log.e("Tag",vdfilmModels.get(i).getTitle());
                    }
                    slideAdapter = new SlideAdapter(getActivity(),vdfilmModels);
                    viewPagerweek.setAdapter(slideAdapter);
                    circleIndicator_week.setViewPager(viewPagerweek);
                    slideAdapter.registerDataSetObserver(circleIndicator_week.getDataSetObserver());

                    if(vdfilmModels==null||vdfilmModels.isEmpty()||viewPager==null)
                        return;
                    if(mtime ==null)
                        mtime = new Timer();

                    mtime.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    int current = viewPagerweek.getCurrentItem();
                                    int total = vdfilmModels.size() -1;
                                    if(current<total){
                                        current++;
                                        viewPagerweek.setCurrentItem(current);
                                    }
                                    else{
                                        viewPagerweek.setCurrentItem(0);
                                    }
                                }
                            });
                        }
                    },500,3000);

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

    private void CreateSlide_person() {
        Film_Api film_api = Service.getApi_film();
        Call<PersonReponse> reponseCall = film_api.TrenddingPerson(
                KeyAPI.API_key,1);
        reponseCall.enqueue(new Callback<PersonReponse>() {
            @Override
            public void onResponse(Call<PersonReponse> call, Response<PersonReponse> response) {
                if(response.isSuccessful())
                {
                    List<PersonModel> vdfilmModels = new ArrayList<>(response.body().getPeson());

                    Log.e("Tag",""+vdfilmModels.size());

                    slideAdapterPerson = new SlideAdapterPerson(getActivity(),vdfilmModels);
                    viewPagerperson.setAdapter(slideAdapterPerson);
                    circleIndicator_person.setViewPager(viewPagerperson);
                    slideAdapterPerson.registerDataSetObserver(circleIndicator_person.getDataSetObserver());

                    if(vdfilmModels==null||vdfilmModels.isEmpty()||viewPagerperson==null)
                        return;
                    if(mtime ==null)
                        mtime = new Timer();

                    mtime.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    int current = viewPagerperson.getCurrentItem();
                                    int total = vdfilmModels.size() -1;
                                    if(current<total){
                                        current++;
                                        viewPagerperson.setCurrentItem(current);
                                    }
                                    else{
                                        viewPagerperson.setCurrentItem(0);
                                    }
                                }
                            });
                        }
                    },500,3000);

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
            public void onFailure(Call<PersonReponse> call, Throwable t) {
                Log.e("Tag","Failed connection");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mtime!=null){
            mtime.cancel();
            mtime = null;

        }
    }
}
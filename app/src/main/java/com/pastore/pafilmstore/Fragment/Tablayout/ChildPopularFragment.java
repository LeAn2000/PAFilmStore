package com.pastore.pafilmstore.Fragment.Tablayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pastore.pafilmstore.DetailsActivity;
import com.pastore.pafilmstore.Model.FilmModel;
import com.pastore.pafilmstore.R;
import com.pastore.pafilmstore.ViewModels.POpListViewModel;
import com.pastore.pafilmstore.adapter.FilmRecycler;
import com.pastore.pafilmstore.adapter.OnListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChildPopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChildPopularFragment extends Fragment implements OnListener {

    private RecyclerView recyclerView;
    private FilmRecycler filmRecycler;
    private POpListViewModel model;
    View mview;
    int page = 1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChildPopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChildPopularFragment newInstance(String param1, String param2) {
        ChildPopularFragment fragment = new ChildPopularFragment();
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
        model = new ViewModelProvider(this).get(POpListViewModel.class);
        mview= inflater.inflate(R.layout.fragment_childpopular, container, false);
        recyclerView = mview.findViewById(R.id.recyclerView_child);
        return mview;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConfigRecyclerView();
        OBserveAnyChange();

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1))
                {
                    model.getnext();
                }
                else
                    return;;

            }
        });
    }



    private void ConfigRecyclerView(){
        filmRecycler = new FilmRecycler(this);

        recyclerView.setAdapter(filmRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

//    private void GetPopular() {
//        Film_Api film_api = Service.getApi_film();
//        Call<FilmSearchReponse> reponseCall = film_api.Popular(
//                KeyAPI.API_key);
//        reponseCall.enqueue(new Callback<FilmSearchReponse>() {
//            @Override
//            public void onResponse(Call<FilmSearchReponse> call, Response<FilmSearchReponse> response) {
//                if(response.isSuccessful())
//                {
//
//                    List<FilmModel> models = new ArrayList<>(response.body().getFilm());
//                    if(models!=null) filmRecycler.setFilmModelList(models);
//                    else
//                        filmRecycler.setFilmModelList(null);
//
//                }
//                else
//                {
//                    try{
//                        Log.v("Tag","Error"+response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FilmSearchReponse> call, Throwable t) {
//
//            }
//        });
//    }

    private void OBserveAnyChange(){
        model.getPOp(1);
        model.getFilm().observe(getViewLifecycleOwner(), new Observer<List<FilmModel>>() {
            @Override
            public void onChanged(List<FilmModel> filmModels) {
                if(filmModels!=null){
                    filmRecycler.setFilmModelList(filmModels);
                }
                else
                {
                    filmRecycler.setFilmModelList(null);
                }
            }
        });
    }

    @Override
    public void OnFilmClick(int pos) {
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra("film",filmRecycler.getSelectedFilm(pos));
        startActivity(intent);

    }

    @Override
    public void OnRecomdClick(int pos) {

    }

    @Override
    public void OnCatagoryClick(String cata) {

    }
}
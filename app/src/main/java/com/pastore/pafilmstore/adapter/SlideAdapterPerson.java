package com.pastore.pafilmstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.pastore.pafilmstore.Detail_Person_Activity;
import com.pastore.pafilmstore.Model.PersonModel;
import com.pastore.pafilmstore.R;

import java.util.List;

public class SlideAdapterPerson extends PagerAdapter {
    private Context context;
    private List<PersonModel> personModels;


    public SlideAdapterPerson(Context context, List<PersonModel> filmModelList) {
        this.context = context;
        this.personModels = filmModelList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo_person,container,false);
        ImageView img = view.findViewById(R.id.img_photo);

        TextView title = view.findViewById(R.id.Name);
        TextView Gender = view.findViewById(R.id.Gender);
        TextView Department = view.findViewById(R.id.department);
        PersonModel ps = personModels.get(position);
        if(ps!=null)
        {
            title.setText(ps.getName());
            if(ps.getGender()==1)
                Gender.setText("Gender: Women");
            else
                Gender.setText("Gender: Man");
            Department.setText("Known for department: "+ps.getKnown_for_department());
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+ps.getProfile_path()).into(img);
        }

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Detail_Person_Activity.class);
                intent.putExtra("film",personModels.get(position));
                ContextCompat.startActivity(context,intent,null);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(personModels!=null)
            return personModels.size();
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

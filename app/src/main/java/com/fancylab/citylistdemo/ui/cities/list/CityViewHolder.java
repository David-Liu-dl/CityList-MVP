package com.fancylab.citylistdemo.ui.cities.list;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.models.City;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class CityViewHolder extends RecyclerView.ViewHolder {

    private View view;

    @BindView(R.id.city_image)
    ImageView cityImageIv;
    @BindView(R.id.city_title)
    TextView cityTitleTv;
    @BindView(R.id.city_description)
    TextView cityDescriptionTv;

    public CityViewHolder(View itemView, PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(v -> clickSubject.onNext(getAdapterPosition()));
    }

    void bind(City city){
        if (TextUtils.isEmpty(city.getImageHref())){
            Picasso.get()
                    .load(R.drawable.res_image_badurl)
                    .into(cityImageIv);
        }else {
            Picasso.get()
                    .load(city.getImageHref())
                    .placeholder(R.drawable.res_image_placeholder)
                    .error(R.drawable.res_image_badurl)
                    .into(cityImageIv);
        }

        cityTitleTv.setText(city.getTitle());
        cityDescriptionTv.setText(city.getDescription());
    }

}

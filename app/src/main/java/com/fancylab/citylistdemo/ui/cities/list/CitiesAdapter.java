package com.fancylab.citylistdemo.ui.cities.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.models.City;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class CitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    private List<City> listCities = new ArrayList<>();

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view ,itemClicks);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = listCities.get(position);
        holder.bind(city);
    }

    @Override
    public int getItemCount() {
        if (listCities != null && listCities.size() > 0) {
            return listCities.size();
        } else {
            return 0;
        }
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    public void refresh(List<City> cities){
        this.listCities.clear();
        this.listCities = cities;
        notifyDataSetChanged();
    }
}

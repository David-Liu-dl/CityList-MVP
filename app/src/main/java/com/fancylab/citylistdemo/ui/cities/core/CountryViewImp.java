package com.fancylab.citylistdemo.ui.cities.core;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.ui.cities.CountryActivity;
import com.fancylab.citylistdemo.ui.cities.list.CitiesAdapter;
import com.fancylab.citylistdemo.ui.cities.list.SpaceItemDecoration;
import com.fancylab.citylistdemo.utils.UiUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryViewImp implements CountryContract.CountryView
        , SwipeRefreshLayout.OnRefreshListener {

    private CountryActivity activity;
    private View view;
    private CitiesAdapter adapter;

    @BindView(R.id.toolbar_country)
    Toolbar toolbar;
    @BindView(R.id.cities_list_refreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.cities_list_recyclerview)
    RecyclerView recyclerView;

    public CountryViewImp(CountryActivity context) {
        this.activity = context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_country, parent, true);
        ButterKnife.bind(this, view);

        initView();
    }

    private void initView(){
        activity.setSupportActionBar(toolbar);

        swipeRefreshLayout.setOnRefreshListener(this);

        final int itemSpace = (int) activity.getResources().getDimension(R.dimen.recylerview_item_space_city);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(activity.convertDpToPx(itemSpace)));

        adapter = new CitiesAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onRefresh() {
        activity.getCountryPresenter().getCountryInfo();
    }

    @Override
    public void displayCountry(Country country) {
        swipeRefreshLayout.setRefreshing(false);

        toolbar.setTitle(country.getTitle());
        adapter.refresh(country.getCities());
    }

    @Override
    public void onError(String msg) {
        swipeRefreshLayout.setRefreshing(false);
        UiUtils.showSnackbar(view, msg, activity.getResources().getInteger(R.integer.duration_snackbar));
    }

    @Override
    public void showSnackNetworkAvailabilityMessage(boolean isAvailable) {
        Context context = view.getContext();
        UiUtils.showSnackbar(view
                , context.getString(isAvailable ? R.string.message_network_available : R.string.message_network_unavailable)
                , context.getResources().getInteger(R.integer.duration_snackbar));
    }
}

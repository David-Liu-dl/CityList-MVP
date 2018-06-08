package com.fancylab.citylistdemo.ui.cities.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.base.AppBaseActivity;
import com.fancylab.citylistdemo.base.DaggerBaseActivity;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.ui.cities.dagger.DaggerCountryComponent;
import com.fancylab.citylistdemo.ui.cities.list.CitiesAdapter;
import com.fancylab.citylistdemo.ui.cities.list.SpaceItemDecoration;
import com.fancylab.citylistdemo.utils.UiUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryActivity extends AppBaseActivity
        implements CountryContract.CountryView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.root_view)
    View view;
    @BindView(R.id.toolbar_country)
    Toolbar toolbar;
    @BindView(R.id.cities_list_refreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.cities_list_recyclerview)
    RecyclerView recyclerView;

    private CountryContract.CountryPresenter countryPresenter;
    private CitiesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        ButterKnife.bind(this);
        initView();
        countryPresenter.onCreate();
    }

    private void initView(){
        setSupportActionBar(toolbar);

        swipeRefreshLayout.setOnRefreshListener(this);

        final int itemSpace = (int) getResources().getDimension(R.dimen.recylerview_item_space_city);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(convertDpToPx(itemSpace)));

        adapter = new CitiesAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryPresenter.onDestroy();
    }

    @Override
    protected void setupDaggerComponent() {
        DaggerCountryComponent
                .builder()
                .appComponentBase(AppApplication.get(this).getAppComponent())
                .countryContextModule(injectionHelper.getCountryContextModule(this))
                .countryModule(injectionHelper.getCountryModule(this))
                .build()
                .inject(this);
    }

    @Inject
    public void setCountryPresenter(CountryContract.CountryPresenter countryPresenter){
        this.countryPresenter = countryPresenter;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onRefresh() {
        countryPresenter.getCountryInfo();
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
        UiUtils.showSnackbar(view, msg, getResources().getInteger(R.integer.duration_snackbar));
    }

    @Override
    public void showSnackNetworkAvailabilityMessage(boolean isAvailable) {
        Context context = view.getContext();
        UiUtils.showSnackbar(view
                , context.getString(isAvailable ? R.string.message_network_available : R.string.message_network_unavailable)
                , context.getResources().getInteger(R.integer.duration_snackbar));
    }
}

package com.fancylab.citylistdemo.ui.cities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.base.BaseActivity;
import com.fancylab.citylistdemo.ui.cities.core.CountryContract;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryContextModule;
import com.fancylab.citylistdemo.ui.cities.dagger.DaggerCountryComponent;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerSplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;

import javax.inject.Inject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryActivity extends BaseActivity {

    private CountryContract.CountryView countryView;
    private CountryContract.CountryPresenter countryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(countryView.getView());
        countryPresenter.onCreate();
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
                .appComponent(AppApplication.get(this).getAppComponent())
                .countryContextModule(new CountryContextModule(this))
                .build()
                .inject(this);
    }

    @Inject
    public void setCountryView(CountryContract.CountryView countryView){
        this.countryView = countryView;
    }

    @Inject
    public void setCountryPresenter(CountryContract.CountryPresenter countryPresenter){
        this.countryPresenter = countryPresenter;
    }

    public CountryContract.CountryPresenter getCountryPresenter() {
        return countryPresenter;
    }
}

package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.utils.NetworkUtils;

import rx.Observable;

/**
 * Created by David Liu on 16/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryModelImp implements CountryContract.CountryModel {

    private CountryActivity context;
    private CountryApi countryApi;

    public CountryModelImp(CountryActivity context, CountryApi countryApi) {
        this.context = context;
        this.countryApi = countryApi;
    }

    @Override
    public Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    @Override
    public void clear() {
        context = null;
    }

    @Override
    public Observable<Country> getCountryInfo() {
        return countryApi.getCountry();
    }

}

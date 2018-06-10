package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.utils.NetworkUtils;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by David Liu on 16/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryModelImp implements CountryContract.CountryModel {

    private CountryActivity context;
    private CountryApi countryApi;

    private Country country;

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
    public Observable<Country> getCountryObservable() {
        return countryApi.getCountry().doOnNext(country ->
                CountryModelImp.this.country = country);
    }

    @Override
    public Observable<City> getCityObservableAtIndex(int index) {
        if (country == null){
            return Observable.just(null);
        }

        return Observable.just(country.getCities().get(index));
    }
}

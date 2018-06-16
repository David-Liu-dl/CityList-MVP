package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.ui.cities.core.CountryContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class CountryViewModule {

    private CountryContract.CountryView countryView;

    public CountryViewModule(CountryContract.CountryView countryView) {
        this.countryView = countryView;
    }

    @CountryScope
    @Provides
    public CountryContract.CountryView provideSplashView() {
        return countryView;
    }
}

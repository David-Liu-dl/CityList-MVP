package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.ui.cities.core.CountryContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestCountryModule {

    private CountryContract.CountryPresenter countryPresenter;

    public TestCountryModule(CountryContract.CountryPresenter countryPresenter) {
        this.countryPresenter = countryPresenter;
    }

    @TestCountryScope
    @Provides
    public CountryContract.CountryPresenter providePresenter() {
        return countryPresenter;
    }

}

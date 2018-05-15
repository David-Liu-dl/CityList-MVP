package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.ui.cities.CountryActivity;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class CountryContextModule {

    private CountryActivity context;

    public CountryContextModule(CountryActivity context) {
        this.context = context;
    }

    @CountryScope
    @Provides
    CountryActivity provideCountryContext() {
        return context;
    }
}

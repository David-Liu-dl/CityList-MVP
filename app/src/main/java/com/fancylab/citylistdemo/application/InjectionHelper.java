package com.fancylab.citylistdemo.application;

import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.cities.core.CountryContract;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryContextModule;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryModule;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashModule;

/**
 * Created by David Liu on 8/6/18.
 * lyhmelbourne@gmail.com
 */

public interface InjectionHelper {
    // Splash Module
    SplashContextModule getSplashContextModule(SplashActivity splashActivity);
    SplashModule getSplashModule(SplashContract.SplashView splashView);
    // Country Module
    CountryContextModule getCountryContextModule(CountryActivity countryActivity);
    CountryModule getCountryModule(CountryContract.CountryView countryView);
}

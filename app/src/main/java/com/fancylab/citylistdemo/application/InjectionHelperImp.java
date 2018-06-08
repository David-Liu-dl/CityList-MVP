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

public class InjectionHelperImp implements InjectionHelper{

    @Override
    public SplashContextModule getSplashContextModule(SplashActivity splashActivity){
        return new SplashContextModule(splashActivity);
    }
    @Override
    public SplashModule getSplashModule(SplashContract.SplashView splashView){
        return new SplashModule(splashView);
    }

    @Override
    public CountryContextModule getCountryContextModule(CountryActivity countryActivity){
        return new CountryContextModule(countryActivity);
    }

    @Override
    public CountryModule getCountryModule(CountryContract.CountryView countryView){
        return new CountryModule(countryView);
    }

}

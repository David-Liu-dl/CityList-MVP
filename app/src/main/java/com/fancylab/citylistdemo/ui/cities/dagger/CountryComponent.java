package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.application.dagger.AppComponent;
import com.fancylab.citylistdemo.ui.cities.CountryActivity;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashScope;

import dagger.Component;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@CountryScope
@Component(modules = {CountryContextModule.class, CountryModule.class}, dependencies = {AppComponent.class})
public interface CountryComponent {
    void inject(CountryActivity activity);
}

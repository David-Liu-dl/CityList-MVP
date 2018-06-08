package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;

import dagger.Component;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@CountryScope
@Component(modules = {CountryContextModule.class, CountryModule.class}, dependencies = {AppComponentBase.class})
public interface CountryComponent {
    void inject(CountryActivity activity);
}

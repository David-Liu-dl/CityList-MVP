package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;

import dagger.Component;

/**
 * Created by David Liu on 16/6/18.
 * lyhmelbourne@gmail.com
 */

@TestCountryScope
@Component(modules = {TestCountryModule.class}, dependencies = {AppComponentBase.class})
public interface TestCountryComponent extends CountryComponent{
}

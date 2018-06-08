package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.ui.cities.core.CountryModelImpTest;

import dagger.Component;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */

@TestScope
@Component(modules = {TestCitiesApiServicesModule.class, TestNetworkModule.class, TestRxModule.class})
public interface TestWebApiComponent {
    void inject(CountryModelImpTest countryModelImpTest);
}

package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivityTest;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivityTest;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import dagger.Component;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */
@TestScope
@Component(modules = {TestCitiesApiServicesModule.class
        , TestNetworkModule.class
        , TestRxModule.class
        , TestAppContextModule.class
        , TestInjectionHelperModule.class})
public interface TestAppComponent extends AppComponentBase {
    RxScheduler rxScheduler();
    CountryApi apiService();
    InjectionHelper injectionHelper();

    void inject(SplashActivityTest splashActivityTest);
    void inject(CountryActivityTest countryActivityTest);
}

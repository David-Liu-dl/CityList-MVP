package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashActivityTest;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashModule;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import javax.inject.Named;

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
}

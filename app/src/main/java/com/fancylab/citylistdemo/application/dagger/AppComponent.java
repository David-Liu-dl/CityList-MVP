package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import dagger.Component;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, CitiesApiServiceModule.class})
public interface AppComponent {

    RxScheduler rxScheduler();
    CountryApi apiService();

}

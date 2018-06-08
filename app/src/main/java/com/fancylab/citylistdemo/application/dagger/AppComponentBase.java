package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.base.DaggerBaseActivity;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public interface AppComponentBase {
    // provider
    RxScheduler rxScheduler();
    CountryApi apiService();
    InjectionHelper injectionHelper();
    // inject
    void inject(DaggerBaseActivity daggerBaseActivity);
}

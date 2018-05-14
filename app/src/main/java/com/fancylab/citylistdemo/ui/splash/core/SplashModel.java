package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BaseViewModel;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;
import com.fancylab.citylistdemo.utils.NetworkUtils;

import rx.Observable;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashModel implements BaseViewModel {

    private SplashActivity splashContext;

    public SplashModel(SplashActivity splashContext) {
        this.splashContext = splashContext;
    }

    Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(splashContext);
    }

    @Override
    public void clear() {
        splashContext = null;
    }
}

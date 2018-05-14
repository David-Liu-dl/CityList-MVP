package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BaseModel;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;
import com.fancylab.citylistdemo.utils.NetworkUtils;

import rx.Observable;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashModel implements BaseModel {

    private SplashActivity splashContext;

    public SplashModel(SplashActivity splashContext) {
        this.splashContext = splashContext;
    }

    @Override
    public Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(splashContext);
    }

    @Override
    public void clear() {
        splashContext = null;
    }
}

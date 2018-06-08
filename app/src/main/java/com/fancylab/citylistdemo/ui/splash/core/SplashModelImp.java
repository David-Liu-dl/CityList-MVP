package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.utils.NetworkUtils;

import rx.Observable;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashModelImp implements SplashContract.SplashModel {

    private SplashActivity context;

    public SplashModelImp(SplashActivity context) {
        this.context = context;
    }

    @Override
    public Observable<Boolean> isNetworkAvailable() {
        return NetworkUtils.isNetworkAvailableObservable(context);
    }

    @Override
    public void clear() {
        context = null;
    }
}

package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BasePresenterImp;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashPresenter extends BasePresenterImp {

    private SplashModel model;
    private SplashView view;

    public SplashPresenter(SplashView view, SplashModel model, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        super(view, model, rxSchedulers, subscriptions);
        this.view = view;
        this.model = model;
    }

}

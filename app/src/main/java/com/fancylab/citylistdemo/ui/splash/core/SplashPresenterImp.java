package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BasePresenterImp;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashPresenterImp extends BasePresenterImp
        implements SplashContract.SplashPresenter{

    private SplashContract.SplashModel model;
    private SplashContract.SplashView view;

    public SplashPresenterImp(SplashContract.SplashView view, SplashContract.SplashModel model, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        super(view, model, rxSchedulers, subscriptions);
        this.view = view;
        this.model = model;
    }

    @Override
    public void onStart() {
        view.showSnackNetworkAvailabilityMessage(true);

        Subscription waitSubscription = Observable
                .just(true)
                .delay(2000, TimeUnit.MILLISECONDS, rxSchedulers.compute())
                .subscribeOn(rxSchedulers.io())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(aLong -> view.intentToCountryActivity(null));

        subscriptions.add(waitSubscription);
    }
}

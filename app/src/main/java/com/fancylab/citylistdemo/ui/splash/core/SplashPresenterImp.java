package com.fancylab.citylistdemo.ui.splash.core;

import android.content.Context;
import android.util.Log;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.ui.base.BasePresenterImp;
import com.fancylab.citylistdemo.utils.UiUtils;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import java.util.concurrent.TimeUnit;

import rx.Observable;
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
        Context context = view.getView().getContext();

        UiUtils.showSnackbar(view.getView()
                , context.getString(R.string.message_network_available)
                , context.getResources().getInteger(R.integer.duration_snackbar));

        subscriptions.add(Observable
                .just(true)
                .delay(context.getResources().getInteger(R.integer.duration_snackbar), TimeUnit.MILLISECONDS)
                .subscribe(aLong -> view.intentToCountryActivity(null)));
    }
}

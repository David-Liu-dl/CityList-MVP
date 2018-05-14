package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BasePresenter;
import com.fancylab.citylistdemo.ui.base.BasePresenterImp;
import com.fancylab.citylistdemo.ui.base.BaseViewModel;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashPresenter extends BasePresenterImp {

    private SplashModel viewModel;

    public SplashPresenter(SplashModel viewModel, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        super(viewModel, rxSchedulers, subscriptions);
        this.viewModel = viewModel;
    }

}

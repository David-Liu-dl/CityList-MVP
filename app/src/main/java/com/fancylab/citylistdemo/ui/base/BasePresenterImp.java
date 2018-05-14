package com.fancylab.citylistdemo.ui.base;

import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class BasePresenterImp implements BasePresenter {
    protected BaseModel viewModel;
    protected RxScheduler rxSchedulers;
    protected CompositeSubscription subscriptions;

    public BasePresenterImp(BaseModel viewModel, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        this.viewModel = viewModel;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        subscriptions.clear();
        viewModel.clear();
    }
}

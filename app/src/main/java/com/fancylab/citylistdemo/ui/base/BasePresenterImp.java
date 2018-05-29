package com.fancylab.citylistdemo.ui.base;

import com.fancylab.citylistdemo.utils.UiUtils;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public abstract class BasePresenterImp implements BasePresenter {

    protected BaseView view;
    protected BaseModel model;
    protected RxScheduler rxSchedulers;
    protected CompositeSubscription subscriptions;

    public BasePresenterImp(BaseView view, BaseModel Model, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        this.view = view;
        this.model = Model;
        this.rxSchedulers = rxSchedulers;
        this.subscriptions = subscriptions;
    }

    @Override
    public void onCreate() {
        checkNetwork();
    }

    @Override
    public void onDestroy() {
        subscriptions.unsubscribe();
        subscriptions.clear();
        model.clear();
    }

    private void checkNetwork(){
        Subscription networkCheckingSubscription = model.isNetworkAvailable()
                .doOnNext(isAvailable -> {
                    if (!isAvailable) {
                        view.showSnackNetworkAvailabilityMessage(isAvailable);
                    }
                })
                .filter((Boolean isNetworkAvailable) -> isNetworkAvailable)
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(aBoolean -> BasePresenterImp.this.onStart(), throwable -> UiUtils.handleThrowable(throwable));

        subscriptions.add(networkCheckingSubscription);
    }
}

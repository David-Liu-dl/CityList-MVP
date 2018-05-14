package com.fancylab.citylistdemo.ui.base;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.utils.UiUtils;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

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
        model.isNetworkAvailable()
                .doOnNext(isAvailable -> {
                   if (!isAvailable){
                       UiUtils.showSnackbar(view.getView()
                               , view.getView().getContext().getString(R.string.message_network_unavailable)
                               , view.getView().getContext().getResources().getInteger(R.integer.duration_snackbar));
                   }
                })
               .filter(isNetworkAvailable -> true)
               .flatMap(isAvailable -> model.isNetworkAvailable())
               .subscribeOn(rxSchedulers.internet())
               .observeOn(rxSchedulers.androidThread()).subscribe(aBoolean -> {
                   // TODO: 15/5/18 prepare work
               }, UiUtils::handleThrowable);
    }

    @Override
    public void onDestroy() {
        subscriptions.clear();
        model.clear();
    }
}

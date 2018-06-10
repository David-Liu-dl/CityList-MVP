package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.ui.base.BasePresenterImp;
import com.fancylab.citylistdemo.utils.UiUtils;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 16/5/18.
 * lyhmelbourne@gmail.com
 */

public class CountryPresenterImp extends BasePresenterImp
        implements CountryContract.CountryPresenter{

    private CountryContract.CountryModel model;
    private CountryContract.CountryView view;

    public CountryPresenterImp(CountryContract.CountryView view, CountryContract.CountryModel model, RxScheduler rxSchedulers, CompositeSubscription subscriptions) {
        super(view, model, rxSchedulers, subscriptions);
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        subscriptions.add(view.itemClicks().subscribe((Integer integer) ->
                model.getCityObservableAtIndex(integer)
                        .filter(city -> city != null)
                        .observeOn(rxSchedulers.androidThread())
                        .subscribeOn(rxSchedulers.io())
                        .subscribe((City city) -> view.displayItemToast(city))));
    }

    @Override
    public void onStart() {
        this.getCountryInfo();
    }

    @Override
    public void getCountryInfo() {
        Subscription subscription = model.isNetworkAvailable()
                .doOnNext(networkAvailable -> {
            if (!networkAvailable) {
                view.showSnackNetworkAvailabilityMessage(networkAvailable);
            }})
                .filter(isNetworkAvailable -> true)
                .flatMap(isAvailable -> model.getCountryObservable())
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe(country -> view.displayCountry(country), (Throwable throwable) -> {
                    UiUtils.handleThrowable(throwable);
                    view.onError(throwable.getMessage());
                }
        );

        subscriptions.add(subscription);
    }
}

package com.fancylab.citylistdemo.ui.cities.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.cities.core.CountryContract;
import com.fancylab.citylistdemo.ui.cities.core.CountryModelImp;
import com.fancylab.citylistdemo.ui.cities.core.CountryPresenterImp;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class CountryModule {

    @CountryScope
    @Provides
    public CountryContract.CountryPresenter providePresenter(RxScheduler schedulers, CountryContract.CountryView view, CountryContract.CountryModel model) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new CountryPresenterImp(view, model, schedulers, compositeSubscription);
    }

    @CountryScope
    @Provides
    public CountryContract.CountryModel provideModel(CountryActivity context, CountryApi countryApi) {
        return new CountryModelImp(context, countryApi);
    }

}

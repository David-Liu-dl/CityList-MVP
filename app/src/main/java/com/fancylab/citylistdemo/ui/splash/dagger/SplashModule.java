package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.core.SplashModelImp;
import com.fancylab.citylistdemo.ui.splash.core.SplashPresenterImp;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class SplashModule {

    @SplashScope
    @Provides
    public SplashContract.SplashPresenter providePresenter(RxScheduler schedulers, SplashContract.SplashView view, SplashContract.SplashModel model) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new SplashPresenterImp(view, model, schedulers, compositeSubscription);
    }

    @SplashScope
    @Provides
    public SplashContract.SplashModel provideSplashModel(SplashActivity context) {
        return new SplashModelImp(context);
    }

}

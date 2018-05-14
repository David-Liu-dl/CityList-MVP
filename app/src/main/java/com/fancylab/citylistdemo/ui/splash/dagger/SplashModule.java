package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashModel;
import com.fancylab.citylistdemo.ui.splash.core.SplashPresenter;
import com.fancylab.citylistdemo.ui.splash.core.SplashView;
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
    SplashPresenter providePresenter(RxScheduler schedulers, SplashView view, SplashModel model) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        return new SplashPresenter(view, model, schedulers, compositeSubscription);
    }

    @SplashScope
    @Provides
    SplashView provideSplashView(SplashActivity context) {
        return new SplashView(context);
    }


    @SplashScope
    @Provides
    SplashModel provideSplashModel(SplashActivity context) {
        return new SplashModel(context);
    }
}

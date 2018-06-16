package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.core.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class SplashViewModule {

    private SplashContract.SplashView splashView;

    public SplashViewModule(SplashContract.SplashView context) {
        this.splashView = context;
    }

    @SplashScope
    @Provides
    public SplashContract.SplashView provideSplashView() {
        return splashView;
    }
}

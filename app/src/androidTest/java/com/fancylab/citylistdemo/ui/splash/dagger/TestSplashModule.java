package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.core.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestSplashModule {

    private SplashContract.SplashPresenter splashPresenter;

    public TestSplashModule(SplashContract.SplashPresenter splashPresenter) {
        this.splashPresenter = splashPresenter;
    }

    @TestSplashScope
    @Provides
    public SplashContract.SplashPresenter providePresenter() {
        return splashPresenter;
    }

}

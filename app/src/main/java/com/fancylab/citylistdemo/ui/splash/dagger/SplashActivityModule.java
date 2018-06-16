package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class SplashActivityModule {

    private SplashActivity splashContext;

    public SplashActivityModule(SplashActivity context) {
        this.splashContext = context;
    }

    @SplashScope
    @Provides
    public SplashActivity provideSplashContext() {
        return splashContext;
    }

}

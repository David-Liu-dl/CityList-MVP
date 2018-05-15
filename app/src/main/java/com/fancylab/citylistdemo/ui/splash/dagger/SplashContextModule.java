package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.ui.splash.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class SplashContextModule {

    private SplashActivity splashContext;

    public SplashContextModule(SplashActivity context) {
        this.splashContext = context;
    }

    @SplashScope
    @Provides
    SplashActivity provideSplashContext() {
        return splashContext;
    }
}

package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.application.dagger.AppComponent;
import com.fancylab.citylistdemo.application.dagger.AppComponentBase;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;

import dagger.Component;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

@SplashScope
@Component(modules = {SplashContextModule.class, SplashModule.class}, dependencies = {AppComponentBase.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}

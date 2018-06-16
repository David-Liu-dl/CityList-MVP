package com.fancylab.citylistdemo.ui.splash.dagger;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;

import dagger.Component;

/**
 * Created by David Liu on 16/6/18.
 * lyhmelbourne@gmail.com
 */

@TestSplashScope
@Component(modules = {TestSplashModule.class}, dependencies = {AppComponentBase.class})
public interface TestSplashComponent extends SplashComponent{
}

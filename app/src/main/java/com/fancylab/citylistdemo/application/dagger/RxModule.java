package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.utils.rx.AppRxSchedulers;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class RxModule {

    @Provides
    RxScheduler provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}

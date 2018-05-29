package com.fancylab.citylistdemo.dagger;

import com.fancylab.citylistdemo.application.dagger.RxModule;
import com.fancylab.citylistdemo.utils.rx.AppRxSchedulers;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestRxModule extends RxModule{

    @TestScope
    @Provides
    @Named("fake")
    RxScheduler provideFakeRxSchedulers() {
        return Mockito.mock(AppRxSchedulers.class);
    }
}

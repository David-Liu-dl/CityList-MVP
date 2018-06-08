package com.fancylab.citylistdemo.utils.rx;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Created by David Liu on 6/6/18.
 * lyhmelbourne@gmail.com
 */
public class AppRxSchedulersTest {

    private AppRxSchedulers appRxSchedulers;

    @Before
    public void setUp() throws Exception {
        appRxSchedulers = new AppRxSchedulers();
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @After
    public void tearDown() throws Exception {
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void getBACKGROUND_SCHEDULERS_when_runOnBackground() throws Exception {
        Assert.assertSame(appRxSchedulers.runOnBackground(), AppRxSchedulers.BACKGROUND_SCHEDULERS);
    }

    @Test
    public void getIOScheduler_when_io() throws Exception {
        Assert.assertSame(appRxSchedulers.io(), Schedulers.io());
    }

    @Test
    public void getComputeScheduler_when_compute() throws Exception {
        Assert.assertSame(appRxSchedulers.compute(), Schedulers.computation());
    }

    @Test
    public void getAndroidThread_when_AndroidThread() throws Exception {
        Assert.assertSame(appRxSchedulers.androidThread(), Schedulers.immediate());
    }

    @Test
    public void getInternetScheduler_when_internet() throws Exception {
        Assert.assertSame(appRxSchedulers.internet(), AppRxSchedulers.INTERNET_SCHEDULERS);
    }

}
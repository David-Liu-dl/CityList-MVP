package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.subscriptions.CompositeSubscription;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by David Liu on 27/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashPresenterImpTest {

    @Mock
    SplashContract.SplashView view;
    @Mock
    SplashContract.SplashModel model;
    @Mock
    RxScheduler rxScheduler;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private TestScheduler testScheduler = new TestScheduler();
    private SplashPresenterImp splashPresenterImp;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        setupSchedulers();
        splashPresenterImp = new SplashPresenterImp(view, model, rxScheduler, subscriptions);
    }

    private void setupSchedulers(){
        when(rxScheduler.internet()).thenReturn(testScheduler);
        when(rxScheduler.androidThread()).thenReturn(testScheduler);
        when(rxScheduler.io()).thenReturn(testScheduler);
        when(rxScheduler.compute()).thenReturn(testScheduler);
        when(rxScheduler.runOnBackground()).thenReturn(testScheduler);
    }

    @Test
    public void callModelCheckNetworkAvailability_when_createdNetworkAvailable(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(true));
        splashPresenterImp.onCreate();
        verify(model, times(1)).isNetworkAvailable();
    }

    @Test
    public void callModelCheckNetworkAvailability_when_createdNetworkUnavailable(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(false));
        splashPresenterImp.onCreate();
        verify(model, times(1)).isNetworkAvailable();
    }

    @Test
    public void callNetworkAvailableSnackbarWithTrue_when_networkAvailable(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(true));
        splashPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, times(1)).showSnackNetworkAvailabilityMessage(eq(true));
    }

    @Test
    public void callNetworkAvailableSnackbarWithFalse_when_networkUnavailable(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(false));
        splashPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, times(1)).showSnackNetworkAvailabilityMessage(eq(false));
    }

    @Test
    public void notCallIntentToCountryActivity_before_networkAvailableAndTimePassed(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(true));
        splashPresenterImp.onCreate();
        // trigger checking network
        testScheduler.triggerActions();
        // simulate time passed 1000 milliseconds
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS);

        verify(view, never()).intentToCountryActivity(any());
    }

    @Test
    public void callIntentToCountryActivity_when_networkAvailableAndTimePassed(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(true));
        splashPresenterImp.onCreate();
        // trigger checking network
        testScheduler.triggerActions();
        // simulate time passed 2000 milliseconds
        testScheduler.advanceTimeBy(2000, TimeUnit.MILLISECONDS);

        verify(view, times(1)).intentToCountryActivity(any());
    }

    @Test
    public void emptySubscriptionAndModelCleared_when_destroyedCalled(){
        when(model.isNetworkAvailable()).thenReturn(Observable.just(true));
        splashPresenterImp.onCreate();
        // trigger checking network
        testScheduler.triggerActions();
        // simulate time passed 2000 milliseconds
        testScheduler.advanceTimeBy(2000, TimeUnit.MILLISECONDS);

        splashPresenterImp.onDestroy();

        verify(model, times(1)).clear();
        assertThat(subscriptions.isUnsubscribed(), is(true));
        assertThat(subscriptions.hasSubscriptions(), is(false));
    }
}
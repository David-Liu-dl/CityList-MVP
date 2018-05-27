package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.TestScheduler;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by David Liu on 27/5/18.
 * lyhmelbourne@gmail.com
 */
public class CountryPresenterImpTest {
    @Mock
    CountryContract.CountryView view;
    @Mock
    CountryContract.CountryModel model;
    @Mock
    RxScheduler rxScheduler;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private TestScheduler testScheduler = new TestScheduler();
    private CountryPresenterImp countryPresenterImp;

    private Country countryInfo = new Country();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        setupSchedulers();
        countryPresenterImp = new CountryPresenterImp(view, model, rxScheduler, subscriptions);
    }

    private void setupSchedulers(){
        when(rxScheduler.internet()).thenReturn(testScheduler);
        when(rxScheduler.androidThread()).thenReturn(testScheduler);
        when(rxScheduler.io()).thenReturn(testScheduler);
        when(rxScheduler.compute()).thenReturn(testScheduler);
        when(rxScheduler.runOnBackground()).thenReturn(testScheduler);
    }

    @Test
    public void showNetworkUnavailableSnackBar_when_created_networkUnavailable_callGetCountryInfo(){
        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(false));
        countryPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, never()).showSnackNetworkAvailabilityMessage(true);
        verify(view, times(1)).showSnackNetworkAvailabilityMessage(false);
    }

    @Test
    public void callViewDisplayCountry_when_created_networkAvailable_getCountryInfoReturnValid(){
        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(true));
        when(model.getCountryInfo()).thenReturn(Observable.just(countryInfo));
        countryPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, times(1)).displayCountry(countryInfo);
    }

    @Test
    public void callViewOnError_when_created_networkAvailable_getCountryInfoReturnInvalid(){
        final String errorMsg = "errorMsg";
        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(true));
        when(model.getCountryInfo()).thenThrow(new RuntimeException(errorMsg));
        countryPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, times(1)).onError(errorMsg);
    }

}
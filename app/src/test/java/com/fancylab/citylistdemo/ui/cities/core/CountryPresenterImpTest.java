package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.utils.rx.RxScheduler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.HttpURLConnection;

import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.observers.TestObserver;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
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
    private CountryContract.CountryView view;
    @Mock
    private CountryContract.CountryModel model;
    @Mock
    private RxScheduler rxScheduler;
    @Mock
    private HttpException httpException;
    @Mock
    private City city;

    private final Observable<Integer> itemClicks = Observable.just(1,2,3,4,5);

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private TestScheduler testScheduler = new TestScheduler();
    private CountryPresenterImp countryPresenterImp;

    private Country countryInfo = new Country();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subbing();
        countryPresenterImp = new CountryPresenterImp(view, model, rxScheduler, subscriptions);
    }

    private void subbing(){
        setupSchedulers();
        when(view.itemClicks()).thenReturn(itemClicks);
        when(model.getCityObservableAtIndex(0)).thenReturn(Observable.just(null));
        when(model.getCityObservableAtIndex(not(eq(0)))).thenReturn(Observable.just(city));
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
        when(model.getCountryObservable()).thenReturn(Observable.just(countryInfo));
        countryPresenterImp.onCreate();
        testScheduler.triggerActions();
        verify(view, times(1)).displayCountry(countryInfo);
    }

    @Test
    public void callViewOnError_when_created_networkAvailable_getCountryInfoReturnInvalid_ERROR401(){
        final String errorMsg = "errorMsg401";

        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(true));
        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_UNAUTHORIZED);
        when(httpException.getMessage()).thenReturn(errorMsg);
        when(model.getCountryObservable()).thenReturn(Observable.error(httpException));

        countryPresenterImp.onCreate();
        testScheduler.triggerActions();

        verify(view, times(1)).onError(errorMsg);
    }

    @Test
    public void callViewOnError_when_created_networkAvailable_getCountryInfoReturnInvalid_ERROR404(){
        final String errorMsg = "errorMsg404";

        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(true));
        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
        when(httpException.getMessage()).thenReturn(errorMsg);

        when(model.getCountryObservable()).thenReturn(Observable.error(httpException));

        countryPresenterImp.onCreate();
        testScheduler.triggerActions();

        verify(view, times(1)).onError(errorMsg);
    }

    @Test
    public void callViewOnError_when_created_networkAvailable_getCountryInfoReturnInvalid_ERROR503(){
        final String errorMsg = "errorMsg503";

        //noinspection unchecked
        when(model.isNetworkAvailable())
                .thenReturn(Observable.just(true), Observable.just(true));
        when(httpException.code()).thenReturn(HttpURLConnection.HTTP_UNAVAILABLE);
        when(httpException.getMessage()).thenReturn(errorMsg);
        when(model.getCountryObservable()).thenReturn(Observable.error(httpException));

        countryPresenterImp.onCreate();
        testScheduler.triggerActions();

        verify(view, times(1)).onError(errorMsg);
    }

}
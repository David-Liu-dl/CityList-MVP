package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.application.dagger.DaggerTestWebApiComponent;
import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.utils.NetworkUtils;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.notNull;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */

@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(NetworkUtils.class)
@RunWith(PowerMockRunner.class)
public class CountryModelImpTest {

    @Inject
    MockWebServer mockWebServer;
    @Inject
    @Named("fake")
    CountryApi countryApi;

    @Mock
    private CountryActivity countryActivity;

    private CountryModelImp countryModelImp;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        DaggerTestWebApiComponent
                .builder()
                .build()
                .inject(this);

        countryModelImp = new CountryModelImp(countryActivity, countryApi);
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void returnNonNullCountryObject_when_analyzeLocalNonNullJson() throws UnsupportedEncodingException {
        String fileName = "country.json";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getJson(fileName)));

        TestSubscriber<Country> testSubscriber = new TestSubscriber<>();
        Observable<Country> countryObservable = countryModelImp.getCountryObservable();

        countryObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

        testSubscriber.assertNoErrors();
        testSubscriber.assertTerminalEvent();
    }

    @Test
    public void returnNonNullCountryObjectWithEmptyValue_when_analyzeLocalNullJson() throws UnsupportedEncodingException {
        String fileName = "empty_country.json";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getJson(fileName)));

        TestSubscriber<Country> testSubscriber = new TestSubscriber<>();
        Observable<Country> countryObservable = countryModelImp.getCountryObservable();

        countryObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent();

        List<Country> countryListResult = testSubscriber.getOnNextEvents();

        testSubscriber.assertNoErrors();
        testSubscriber.assertTerminalEvent();

        assertEquals(1, countryListResult.size());

        Country result = countryListResult.get(0);

        assertEquals(true, result.getTitle() == null || result.getTitle().equals(""));
        assertEquals(0, result.getCities().size());
    }

    @Test
    public void returnNullObservable_when_countryIsNull() throws Exception{
        Observable<City> observable = countryModelImp.getCityObservableAtIndex(0);
        TestSubscriber<City> testSubscriber = new TestSubscriber<>();

        observable.subscribe(testSubscriber);

        testSubscriber.assertValue(null);
    }

    @Test
    public void returnNonNullObservable_when_countryIsNull() throws Exception{
        String fileName = "country.json";

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(getJson(fileName)));

        TestSubscriber<Country> testSubscriberCountry = new TestSubscriber<>();
        countryModelImp.getCountryObservable().subscribe(testSubscriberCountry);
        testSubscriberCountry.awaitTerminalEvent();

        Observable<City> observable = countryModelImp.getCityObservableAtIndex(0);
        TestSubscriber<City> testSubscriberCity = new TestSubscriber<>();
        observable.subscribe(testSubscriberCity);
        testSubscriberCity.awaitTerminalEvent();

        testSubscriberCity.assertValue(
                testSubscriberCountry
                        .getOnNextEvents()
                        .get(0)
                        .getCities()
                        .get(0));
    }

    @Test
    public void releaseContext_when_clear(){
        CountryActivity contextBefore = Whitebox.getInternalState(countryModelImp, "context");
        Assert.assertNotNull(contextBefore);
        countryModelImp.clear();
        CountryActivity contextAfter = Whitebox.getInternalState(countryModelImp, "context");
        Assert.assertNull(contextAfter);
    }

    @Test
    public void returnUtilsValue_when_isNetworkAvailable(){
        mockStatic(NetworkUtils.class);
        when(NetworkUtils.isNetworkAvailableObservable(any())).thenReturn(Observable.just(any()));
        Assert.assertEquals(countryModelImp.isNetworkAvailable(), NetworkUtils.isNetworkAvailableObservable(any()));
    }

    private String getJson(String filename) throws UnsupportedEncodingException {
        ClassLoader  loader = this.getClass().getClassLoader();

        BufferedReader in = new BufferedReader(new InputStreamReader(
                loader.getResourceAsStream(filename),"UTF-8"
        ));

        return in.lines()
                .parallel()
                .collect(Collectors.joining());
    }
}
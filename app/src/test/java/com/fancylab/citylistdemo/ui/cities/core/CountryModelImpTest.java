package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.dagger.DaggerTestWebApiComponent;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.ui.cities.CountryActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */
public class CountryModelImpTest {

    @Inject
    MockWebServer mockWebServer;
    @Inject
    @Named("fake")
    CountryApi countryApi;

    @Mock
    CountryActivity countryActivity;

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
        Observable<Country> countryObservable = countryModelImp.getCountryInfo();

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
        Observable<Country> countryObservable = countryModelImp.getCountryInfo();

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
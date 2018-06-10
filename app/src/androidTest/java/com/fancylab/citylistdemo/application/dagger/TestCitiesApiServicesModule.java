package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.models.Country;

import org.mockito.Mockito;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestCitiesApiServicesModule{

    @TestScope
    @Provides
    public CountryApi provideFakeApiService(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter) {
        return Mockito.mock(CountryApi.class);
    }
}

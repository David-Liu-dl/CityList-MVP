package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.utils.rx.AppRxSchedulers;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestNetworkModule {

    @Provides
    OkHttpClient provideHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        return builder.build();
    }

    @Provides
    RxJavaCallAdapterFactory provideRxAdapter() {
        return RxJavaCallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS);
    }

    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }

}

package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.api.CountryApi;
import com.fancylab.citylistdemo.base.C;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.PUT;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class CitiesApiServiceModule {

    @AppScope
    @Provides
    public CountryApi provideApiService(OkHttpClient client, GsonConverterFactory gson, RxJavaCallAdapterFactory rxAdapter)
    {
        Retrofit retrofit =   new Retrofit.Builder().client(client)
                .baseUrl(C.remote.HOST).addConverterFactory(gson).
                        addCallAdapterFactory(rxAdapter).build();

        return  retrofit.create(CountryApi.class);
    }

}

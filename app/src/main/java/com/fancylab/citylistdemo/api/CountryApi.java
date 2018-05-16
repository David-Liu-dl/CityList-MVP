package com.fancylab.citylistdemo.api;

import com.fancylab.citylistdemo.models.Country;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public interface CountryApi {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<Country> getCountry();

}

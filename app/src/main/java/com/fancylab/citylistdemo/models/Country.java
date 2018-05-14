package com.fancylab.citylistdemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class Country {
    @Expose
    @SerializedName("rows")
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}

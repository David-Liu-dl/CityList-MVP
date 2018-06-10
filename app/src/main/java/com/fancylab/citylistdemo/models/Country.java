package com.fancylab.citylistdemo.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class Country implements Serializable{

    @Expose
    private String title = "";

    @Expose
    @SerializedName("rows")
    private List<City> cities;

    public Country() {
    }

    public Country(String title, List<City> cities) {
        this.title = title;
        this.cities = cities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}

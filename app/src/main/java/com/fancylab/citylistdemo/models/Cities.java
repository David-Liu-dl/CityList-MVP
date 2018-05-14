package com.fancylab.citylistdemo.models;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class Cities {
    @Expose
    private List<City> elements;

    public List<City> getElements() {
        return elements;
    }

    public void setElements(List<City> elements) {
        this.elements = elements;
    }
}

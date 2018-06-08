package com.fancylab.citylistdemo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by David Liu on 6/6/18.
 * lyhmelbourne@gmail.com
 */
public class CountryTest {

    private static final String COUNTRY_TITLE1 = "title1";
    private static final String COUNTRY_TITLE2 = "title2";

    private static final List<City> CITY_LIST_1 = new ArrayList<>();
    private static final List<City> CITY_LIST_2 = new ArrayList<>();

    private Country country;

    @Before
    public void setUp() throws Exception {
        country = new Country();
    }

    @Test
    public void getTitle() throws Exception {
        country.setTitle(COUNTRY_TITLE1);
        Assert.assertEquals(country.getTitle(), COUNTRY_TITLE1);
    }

    @Test
    public void setTitle() throws Exception {
        country.setTitle(COUNTRY_TITLE1);
        Assert.assertEquals(country.getTitle(), COUNTRY_TITLE1);
        country.setTitle(COUNTRY_TITLE2);
        Assert.assertEquals(country.getTitle(), COUNTRY_TITLE2);
    }

    @Test
    public void getCities() throws Exception {
        country.setCities(CITY_LIST_1);
        Assert.assertSame(country.getCities(), CITY_LIST_1);
    }

    @Test
    public void setCities() throws Exception {
        country.setCities(CITY_LIST_1);
        Assert.assertSame(country.getCities(), CITY_LIST_1);
        country.setCities(CITY_LIST_2);
        Assert.assertSame(country.getCities(), CITY_LIST_2);
    }

}
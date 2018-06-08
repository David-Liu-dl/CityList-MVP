package com.fancylab.citylistdemo.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by David Liu on 6/6/18.
 * lyhmelbourne@gmail.com
 */
public class CityTest {

    private static final String CITY_TITLE1 = "title1";
    private static final String CITY_TITLE2 = "title2";
    private static final String CITY_DESCRIPTION1 = "description1";
    private static final String CITY_DESCRIPTION2= "description2";
    private static final String CITY_IMAGEHREF1 = "imageHref1";
    private static final String CITY_IMAGEHREF2 = "imageHref2";

    private City city;

    @Before
    public void setUp() throws Exception {
        city = new City();
    }

    @Test
    public void getTitle() throws Exception {
        city.setTitle(CITY_TITLE1);
        Assert.assertEquals(city.getTitle(), CITY_TITLE1);
    }

    @Test
    public void setTitle() throws Exception {
        city.setTitle(CITY_TITLE1);
        Assert.assertEquals(city.getTitle(), CITY_TITLE1);
        city.setTitle(CITY_TITLE2);
        Assert.assertEquals(city.getTitle(), CITY_TITLE2);
    }

    @Test
    public void getDescription() throws Exception {
        city.setDescription(CITY_DESCRIPTION1);
        Assert.assertEquals(city.getDescription(), CITY_DESCRIPTION1);
    }

    @Test
    public void setDescription() throws Exception {
        city.setDescription(CITY_DESCRIPTION1);
        Assert.assertEquals(city.getDescription(), CITY_DESCRIPTION1);
        city.setDescription(CITY_DESCRIPTION2);
        Assert.assertEquals(city.getDescription(), CITY_DESCRIPTION2);
    }

    @Test
    public void getImageHref() throws Exception {
        city.setImageHref(CITY_IMAGEHREF1);
        Assert.assertEquals(city.getImageHref(), CITY_IMAGEHREF1);
    }

    @Test
    public void setImageHref() throws Exception {
        city.setImageHref(CITY_IMAGEHREF1);
        Assert.assertEquals(city.getImageHref(), CITY_IMAGEHREF1);
        city.setImageHref(CITY_IMAGEHREF2);
        Assert.assertEquals(city.getImageHref(), CITY_IMAGEHREF2);
    }

}
package com.fancylab.citylistdemo.application;

import android.content.Context;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;
import com.fancylab.citylistdemo.application.dagger.DaggerTestAppComponent;
import com.fancylab.citylistdemo.application.dagger.TestAppContextModule;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.cities.core.CountryContract;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryComponent;
import com.fancylab.citylistdemo.ui.cities.dagger.DaggerTestCountryComponent;
import com.fancylab.citylistdemo.ui.cities.dagger.TestCountryModule;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerTestSplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.TestSplashModule;

import junit.framework.Assert;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class TestAppApplication extends AppApplication {

    private TestSplashModule testSplashModule;
    private TestCountryModule testCountryModule;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initAppComponent(){
        appComponent = DaggerTestAppComponent
                .builder()
                .testAppContextModule(new TestAppContextModule(this))
                .build();
    }

    @Override
    public AppComponentBase getAppComponent() {
        if (appComponent == null){
            initAppComponent();
        }
        return appComponent;
    }

    public static TestAppApplication get(Context context) {
        return (TestAppApplication) context.getApplicationContext();
    }

    @Override
    public SplashComponent getSplashComponent(SplashActivity splashActivity, SplashContract.SplashView splashView) {
        Assert.assertNotNull(testSplashModule);

        return DaggerTestSplashComponent
                .builder()
                .appComponentBase(getAppComponent())
                .testSplashModule(testSplashModule)
                .build();
    }

    @Override
    public CountryComponent getCountryComponent(CountryActivity countryActivity, CountryContract.CountryView countryView) {
        Assert.assertNotNull(testCountryModule);

        return DaggerTestCountryComponent
                .builder()
                .appComponentBase(getAppComponent())
                .testCountryModule(testCountryModule)
                .build();
    }

    public void setTestSplashModule(TestSplashModule testSplashModule) {
        this.testSplashModule = testSplashModule;
    }

    public void setTestCountryModule(TestCountryModule testCountryModule) {
        this.testCountryModule = testCountryModule;
    }
}

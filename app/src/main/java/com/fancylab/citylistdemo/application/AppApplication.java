package com.fancylab.citylistdemo.application;

import android.app.Application;
import android.content.Context;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;
import com.fancylab.citylistdemo.application.dagger.AppContextModule;
import com.fancylab.citylistdemo.application.dagger.DaggerAppComponent;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.cities.core.CountryContract;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryActivityModule;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryComponent;
import com.fancylab.citylistdemo.ui.cities.dagger.CountryViewModule;
import com.fancylab.citylistdemo.ui.cities.dagger.DaggerCountryComponent;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerSplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashActivityModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashViewModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class AppApplication extends Application {

    protected static AppComponentBase appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialiseLogger();
        initLeakCanary();
    }

    private void initialiseLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    //TODO  decide what to log in release version
                }
            });
        }
    }

    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent
                .builder()
                .appContextModule(new AppContextModule(this))
                .build();
    }

    public AppComponentBase getAppComponent() {
        if (appComponent == null){
            initAppComponent();
        }
        return appComponent;
    }

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

    public SplashComponent getSplashComponent(SplashActivity splashActivity, SplashContract.SplashView splashView){
        return DaggerSplashComponent
                .builder()
                .appComponentBase(getAppComponent())
                .splashActivityModule(new SplashActivityModule(splashActivity))
                .splashViewModule(new SplashViewModule(splashView))
                .build();
    }

    public CountryComponent getCountryComponent(CountryActivity countryActivity, CountryContract.CountryView countryView){
        return DaggerCountryComponent
                .builder()
                .appComponentBase(getAppComponent())
                .countryActivityModule(new CountryActivityModule(countryActivity))
                .countryViewModule(new CountryViewModule(countryView))
                .build();
    }

}

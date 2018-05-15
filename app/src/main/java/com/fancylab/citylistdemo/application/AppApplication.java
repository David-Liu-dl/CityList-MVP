package com.fancylab.citylistdemo.application;

import android.app.Application;
import android.content.Context;

import com.fancylab.citylistdemo.application.dagger.AppComponent;
import com.fancylab.citylistdemo.application.dagger.AppContextModule;
import com.fancylab.citylistdemo.application.dagger.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import timber.log.BuildConfig;
import timber.log.Timber;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class AppApplication extends Application {

    private static AppComponent appComponent;

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

    public AppComponent getAppComponent() {
        if (appComponent == null){
            initAppComponent();
        }
        return appComponent;
    }

    public static AppApplication get(Context context) {
        return (AppApplication) context.getApplicationContext();
    }

}

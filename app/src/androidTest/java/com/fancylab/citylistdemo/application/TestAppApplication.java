package com.fancylab.citylistdemo.application;

import android.content.Context;

import com.fancylab.citylistdemo.application.dagger.AppComponentBase;
import com.fancylab.citylistdemo.application.dagger.DaggerTestAppComponent;
import com.fancylab.citylistdemo.application.dagger.TestAppContextModule;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public class TestAppApplication extends AppApplication {

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

}

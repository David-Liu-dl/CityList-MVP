package com.fancylab.citylistdemo.application.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestAppContextModule {

    private final Context context;

    public TestAppContextModule(Context aContext) {
        this.context = aContext;
    }

    @TestScope
    @Provides
    Context provideAppContext() {
        return context;
    }
}

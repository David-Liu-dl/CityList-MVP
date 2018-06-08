package com.fancylab.citylistdemo.application.dagger;

import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.application.InjectionHelperImp;

import org.mockito.Mockito;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David Liu on 8/6/18.
 * lyhmelbourne@gmail.com
 */

@Module
public class TestInjectionHelperModule {

    @TestScope
    @Provides
    public InjectionHelper provideInjectionHelper(){
        return Mockito.spy(InjectionHelperImp.class);
    }

}

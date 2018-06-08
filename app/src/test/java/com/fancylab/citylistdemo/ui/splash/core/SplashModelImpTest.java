package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.utils.NetworkUtils;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import rx.Observable;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by David Liu on 7/6/18.
 * lyhmelbourne@gmail.com
 */

@PrepareForTest(NetworkUtils.class)
@RunWith(PowerMockRunner.class)
public class SplashModelImpTest {

    @Mock
    private SplashActivity splashActivity;

    private SplashModelImp splashModelImp;

    @Before
    public void setUp() throws Exception {
        splashModelImp = new SplashModelImp(splashActivity);
    }

    @Test
    public void releaseContext_when_clear(){
        SplashActivity contextBefore = Whitebox.getInternalState(splashModelImp, "context");
        Assert.assertNotNull(contextBefore);
        splashModelImp.clear();
        SplashActivity contextAfter = Whitebox.getInternalState(splashModelImp, "context");
        Assert.assertNull(contextAfter);
    }

    @Test
    public void returnUtilsValue_when_isNetworkAvailable(){
        mockStatic(NetworkUtils.class);
        when(NetworkUtils.isNetworkAvailableObservable(any())).thenReturn(Observable.just(any()));
        Assert.assertEquals(splashModelImp.isNetworkAvailable(), NetworkUtils.isNetworkAvailableObservable(any()));
    }
}
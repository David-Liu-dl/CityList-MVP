package com.fancylab.citylistdemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by David Liu on 7/6/18.
 * lyhmelbourne@gmail.com
 */

@PrepareForTest(NetworkUtils.class)
@RunWith(PowerMockRunner.class)
public class NetworkUtilsTest {

    @Mock
    private Context context;
    @Mock
    private ConnectivityManager connectivityManager;
    @Mock
    private NetworkInfo networkInfo;

    @Before
    public void setUp() throws Exception {
        when(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager);
        when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);
    }

    @Test
    public void returnTrue_when_isNetworkAvailableWithTrue() throws Exception {
        when(networkInfo.isConnected()).thenReturn(true);
        boolean result = Whitebox.invokeMethod(NetworkUtils.class, "isNetworkAvailable", context);
        Assert.assertEquals(result, true);
    }

    @Test
    public void returnFalse_when_isNetworkAvailableWithFalse() throws Exception {
        when(networkInfo.isConnected()).thenReturn(false);
        boolean result = Whitebox.invokeMethod(NetworkUtils.class, "isNetworkAvailable", context);
        Assert.assertEquals(result, false);
    }

    @Test
    public void returnFalse_when_isNetworkAvailableWithNetworkInfoIsNull() throws Exception {
        networkInfo = null;
        boolean result = Whitebox.invokeMethod(NetworkUtils.class, "isNetworkAvailable", context);
        Assert.assertEquals(result, false);
    }

    @Test
    public void returnTrueObservable_when_isNetworkAvailableWithTrue() throws Exception {
        final boolean expectation = true;
        spy(NetworkUtils.class);
        PowerMockito.doReturn(expectation).when(NetworkUtils.class, "isNetworkAvailable", context);
        TestSubscriber<Boolean> testSubscriber = TestSubscriber.create();
        NetworkUtils.isNetworkAvailableObservable(context).subscribe(testSubscriber);

        testSubscriber.assertValue(expectation);
    }

    @Test
    public void returnFalseObservable_when_isNetworkAvailableWithFalse() throws Exception {
        final boolean expectation = false;
        spy(NetworkUtils.class);
        PowerMockito.doReturn(expectation).when(NetworkUtils.class, "isNetworkAvailable", context);
        TestSubscriber<Boolean> testSubscriber = TestSubscriber.create();
        NetworkUtils.isNetworkAvailableObservable(context).subscribe(testSubscriber);

        testSubscriber.assertValue(expectation);
    }

}
package com.fancylab.citylistdemo.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import timber.log.Timber;

import static org.mockito.Matchers.eq;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by David Liu on 7/6/18.
 * lyhmelbourne@gmail.com
 */

@PrepareForTest({UiUtils.class, Timber.class})
@RunWith(PowerMockRunner.class)
public class UiUtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void callTimber_when_handleThrowable() throws Exception {
        final String errorMsg = "errorMsg";
        final Throwable throwable = Mockito.mock(Throwable.class);

        mockStatic(Timber.class);

        when(throwable.toString()).thenReturn(errorMsg);
        doNothing().when(Timber.class, "e", throwable, errorMsg);

        UiUtils.handleThrowable(throwable);
        verifyStatic();

        Timber.e(eq(throwable), eq(errorMsg));
    }
}
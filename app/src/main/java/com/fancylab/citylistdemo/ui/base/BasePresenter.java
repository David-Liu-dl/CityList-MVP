package com.fancylab.citylistdemo.ui.base;

import android.os.Bundle;

import com.fancylab.citylistdemo.base.BaseActivity;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public interface BasePresenter {
    /**
     * Called when presenter is created
     * in {@link com.fancylab.citylistdemo.base.BaseActivity#onCreate(Bundle)}.
     */
    void onCreate();

    /**
     * Called after {@link BasePresenter#onCreate()} when network is available.
     */
    void onStart();

    /**
     * Called when view is destroyed
     * in {@link BaseActivity#onDestroy()}.
     */
    void onDestroy();
}

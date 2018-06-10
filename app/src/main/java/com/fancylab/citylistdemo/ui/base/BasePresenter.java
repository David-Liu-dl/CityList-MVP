package com.fancylab.citylistdemo.ui.base;

import android.os.Bundle;

import com.fancylab.citylistdemo.base.DaggerBaseActivity;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public interface BasePresenter {
    /**
     * Called when presenter is created
     * in {@link DaggerBaseActivity#onCreate(Bundle)}.
     */
    void onCreate();

    /**
     * Called after {@link BasePresenter#onCreate()} when network is available.
     */
    void onStart();

    /**
     * Called when view is destroyed
     * in {@link DaggerBaseActivity#onDestroy()}.
     */
    void onDestroy();
}

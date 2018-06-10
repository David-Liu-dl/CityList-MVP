package com.fancylab.citylistdemo.base;

import android.util.DisplayMetrics;

import com.fancylab.citylistdemo.ui.base.BaseView;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public abstract class AppBaseActivity extends DaggerBaseActivity
        implements BaseView{

    public int convertDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

}

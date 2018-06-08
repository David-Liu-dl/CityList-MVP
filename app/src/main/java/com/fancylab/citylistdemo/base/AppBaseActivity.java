package com.fancylab.citylistdemo.base;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.application.InjectionHelperImp;
import com.fancylab.citylistdemo.ui.base.BaseView;

import javax.inject.Inject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public abstract class AppBaseActivity extends DaggerBaseActivity
        implements BaseView{

    public int convertDpToPx(int dp){
        return Math.round(dp*(getResources().getDisplayMetrics().xdpi/ DisplayMetrics.DENSITY_DEFAULT));
    }

    public int convertPxToDp(int px){
        return Math.round(px/(Resources.getSystem().getDisplayMetrics().xdpi/DisplayMetrics.DENSITY_DEFAULT));
    }

}

package com.fancylab.citylistdemo.ui.splash.core;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.base.BaseActivity;
import com.fancylab.citylistdemo.base.C;
import com.fancylab.citylistdemo.ui.cities.CountryActivity;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;

import java.io.Serializable;

import butterknife.ButterKnife;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashViewImp implements SplashContract.SplashView {

    private BaseActivity activity;
    private View view;

    public SplashViewImp(SplashActivity context) {
        this.activity = context;
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, parent, true);
        ButterKnife.bind(view, context);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends Serializable> void intentToCountryActivity(T data) {
        Intent intent = new Intent(activity, CountryActivity.class);
        intent.putExtra(C.data.DATA_COUNTRY, data);
        activity.startActivity(intent);
    }

}

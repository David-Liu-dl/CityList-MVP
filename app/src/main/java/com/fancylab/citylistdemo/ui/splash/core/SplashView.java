package com.fancylab.citylistdemo.ui.splash.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.ui.base.BaseView;
import com.fancylab.citylistdemo.ui.splash.SplashActivity;

import butterknife.ButterKnife;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public class SplashView implements BaseView{

    private View view;

    public SplashView(SplashActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, parent, true);
        ButterKnife.bind(view, context);
    }

    @Override
    public View getView() {
        return view;
    }
}

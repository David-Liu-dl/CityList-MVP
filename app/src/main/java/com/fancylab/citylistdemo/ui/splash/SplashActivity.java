package com.fancylab.citylistdemo.ui.splash;

import android.os.Bundle;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.base.BaseActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashPresenter;
import com.fancylab.citylistdemo.ui.splash.core.SplashView;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerSplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private SplashView splashView;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(splashView.getView());
    }

    @Override
    protected void setupDaggerComponent() {
        DaggerSplashComponent
                .builder()
                .appComponent(AppApplication.get(this).getAppComponent())
                .splashContextModule(new SplashContextModule(this))
                .build()
                .inject(this);
    }

    @Inject
    public void setSplashView(SplashView splashView){
        this.splashView = splashView;
    }

    @Inject
    public void setSplashPresenter(SplashPresenter splashPresenter){
        this.splashPresenter = splashPresenter;
    }

}

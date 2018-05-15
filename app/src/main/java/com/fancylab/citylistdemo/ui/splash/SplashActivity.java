package com.fancylab.citylistdemo.ui.splash;

import android.os.Bundle;

import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.base.BaseActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.core.SplashPresenterImp;
import com.fancylab.citylistdemo.ui.splash.core.SplashViewImp;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerSplashComponent;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private SplashContract.SplashView splashView;
    private SplashContract.SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(splashView.getView());
        splashPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashPresenter.onDestroy();
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
    public void setSplashView(SplashContract.SplashView splashView){
        this.splashView = splashView;
    }

    @Inject
    public void setSplashPresenter(SplashContract.SplashPresenter splashPresenter){
        this.splashPresenter = splashPresenter;
    }

}

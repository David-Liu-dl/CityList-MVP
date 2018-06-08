package com.fancylab.citylistdemo.ui.splash.core;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.base.AppBaseActivity;
import com.fancylab.citylistdemo.base.DaggerBaseActivity;
import com.fancylab.citylistdemo.base.C;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.splash.dagger.DaggerSplashComponent;
import com.fancylab.citylistdemo.utils.UiUtils;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppBaseActivity
        implements SplashContract.SplashView{

    @BindView(R.id.root_view)
    View view;

    private SplashContract.SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
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
                .appComponentBase(AppApplication.get(this).getAppComponent())
                .splashContextModule(injectionHelper.getSplashContextModule(this))
                .splashModule(injectionHelper.getSplashModule(this))
                .build()
                .inject(this);
    }

    @Inject
    public void setSplashPresenter(SplashContract.SplashPresenter splashPresenter){
        this.splashPresenter = splashPresenter;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends Serializable> void intentToCountryActivity(T data) {
        Intent intent = new Intent(this, CountryActivity.class);
        intent.putExtra(C.data.DATA_COUNTRY, data);
        startActivity(intent);
    }

    @Override
    public void showSnackNetworkAvailabilityMessage(boolean isAvailable) {
        Context context = view.getContext();
        UiUtils.showSnackbar(view
                , context.getString(isAvailable ? R.string.message_network_available : R.string.message_network_unavailable)
                , context.getResources().getInteger(R.integer.duration_snackbar));
    }

}

package com.fancylab.citylistdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fancylab.citylistdemo.application.AppApplication;
import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.ui.base.BaseView;

import javax.inject.Inject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public abstract class DaggerBaseActivity extends AppCompatActivity {

    @Inject
    protected InjectionHelper injectionHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupInjectionHelper();
        setupDaggerComponent();
    }

    private void setupInjectionHelper(){
        AppApplication.get(this).getAppComponent().inject(this);
    }

    protected abstract void setupDaggerComponent();

}

package com.fancylab.citylistdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.fancylab.citylistdemo.application.AppApplication;

import javax.inject.Inject;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public abstract class DaggerBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupDaggerComponent();
    }

    protected AppApplication getAppApplication(){
        return AppApplication.get(this);
    }

    protected abstract void setupDaggerComponent();

}

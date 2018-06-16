package com.fancylab.citylistdemo.ui.splash.core;

import com.fancylab.citylistdemo.ui.base.BaseModel;
import com.fancylab.citylistdemo.ui.base.BasePresenter;
import com.fancylab.citylistdemo.ui.base.BaseView;

import java.io.Serializable;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public interface SplashContract {

    interface SplashView extends BaseView{
        <T extends Serializable> void intentToCountryActivity(T data);
    }

    interface SplashPresenter extends BasePresenter{

    }

    interface SplashModel extends BaseModel{

    }

}

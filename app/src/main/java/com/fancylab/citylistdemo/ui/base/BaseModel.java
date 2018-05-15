package com.fancylab.citylistdemo.ui.base;

import rx.Observable;

/**
 * Created by David Liu on 15/5/18.
 * lyhmelbourne@gmail.com
 */

public interface BaseModel {
    Observable<Boolean> isNetworkAvailable();
    void clear();
}

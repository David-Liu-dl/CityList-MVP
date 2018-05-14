package com.fancylab.citylistdemo.utils.rx;

import rx.Scheduler;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */

public interface RxScheduler {
    Scheduler runOnBackground();

    Scheduler io();

    Scheduler compute();

    Scheduler androidThread();

    Scheduler internet();
}

package com.fancylab.citylistdemo.application.dagger;

import dagger.Component;

/**
 * Created by David Liu on 28/5/18.
 * lyhmelbourne@gmail.com
 */
@TestScope
@Component(modules = {TestCitiesApiServicesModule.class
        , TestNetworkModule.class
        , TestRxModule.class
        , TestAppContextModule.class})
public interface TestAppComponent extends AppComponentBase {
}

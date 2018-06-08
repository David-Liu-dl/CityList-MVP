package com.fancylab.citylistdemo.application.dagger;

import dagger.Component;

/**
 * Created by David Liu on 8/6/18.
 * lyhmelbourne@gmail.com
 */

@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, CitiesApiServiceModule.class, InjectionHelperModule.class})
public interface AppComponent extends AppComponentBase {
}

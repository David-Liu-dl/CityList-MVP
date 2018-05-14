package com.fancylab.citylistdemo.application.dagger;

import dagger.Component;

/**
 * Created by David Liu on 14/5/18.
 * lyhmelbourne@gmail.com
 */
@AppScope
@Component(modules = {NetworkModule.class, AppContextModule.class, RxModule.class, CitiesApiServiceModule.class})
public interface AppComponent {

}

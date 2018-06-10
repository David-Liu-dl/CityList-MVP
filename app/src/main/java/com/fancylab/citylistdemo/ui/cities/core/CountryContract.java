package com.fancylab.citylistdemo.ui.cities.core;

import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.ui.base.BaseModel;
import com.fancylab.citylistdemo.ui.base.BasePresenter;
import com.fancylab.citylistdemo.ui.base.BaseView;

import rx.Observable;

/**
 * Created by David Liu on 16/5/18.
 * lyhmelbourne@gmail.com
 */

public interface CountryContract {

    interface CountryView extends BaseView{
        void displayCountry(Country country);
        Observable<Integer> getItemClicks();
        void displayItemToast(City city);
        void onError(String msg);
    }

    interface CountryPresenter extends BasePresenter{
        void getCountryInfo();
    }

    interface CountryModel extends BaseModel{
        Observable<Country> getCountryObservable();
        Observable<City> getCityObservableAtIndex(int index);
    }

}

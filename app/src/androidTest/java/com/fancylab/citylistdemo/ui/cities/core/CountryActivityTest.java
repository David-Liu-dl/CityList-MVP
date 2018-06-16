package com.fancylab.citylistdemo.ui.cities.core;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.application.TestAppApplication;
import com.fancylab.citylistdemo.models.City;
import com.fancylab.citylistdemo.models.Country;
import com.fancylab.citylistdemo.ui.cities.dagger.TestCountryModule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.fancylab.citylistdemo.MatcherUtil.isRefreshing;
import static com.fancylab.citylistdemo.MatcherUtil.withCity;
import static com.fancylab.citylistdemo.MatcherUtil.withToast;
import static com.fancylab.citylistdemo.ViewAssertionsUtil.hasHolderItemAtPosition;
import static com.fancylab.citylistdemo.ViewAssertionsUtil.hasItemsCount;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by David Liu on 7/6/18.
 * lyhmelbourne@gmail.com
 */

@RunWith(MockitoJUnitRunner.class)
public class CountryActivityTest {

    private static final String ERROR_MSG = "ERROR_MSG";
    private static final String CITY_IMAGEHREF = "CITY_IMAGEHREF";
    private static final String CITY_TITLE = "CITY_TITLE";
    private static final String CITY_DESCRIPTION = "CITY_DESCRIPTION";
    private static final City CITY = new City(CITY_TITLE, CITY_DESCRIPTION, CITY_IMAGEHREF);

    private static final String COUNTRY_TITLE = "COUNTRY_TITLE";
    private static final List<City> COUNTRY_CITIES = new ArrayList<>(Collections.singletonList(CITY));
    private static final Country COUNTRY = new Country(COUNTRY_TITLE, COUNTRY_CITIES);

    @Rule
    public ActivityTestRule<CountryActivity> countryActivityActivityTestRule = new ActivityTestRule<>(CountryActivity.class, true, false);
    @Rule
    public GrantPermissionRule internetPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET);

    @Mock
    private CountryContract.CountryPresenter countryPresenter;

    private Context context;

    private CountryContract.CountryView countryView;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        TestAppApplication app = (TestAppApplication) context;
        app.setTestCountryModule(new TestCountryModule(countryPresenter));

        Intents.init();
        Mockito.reset(countryPresenter);

        stubbing();
        countryActivityActivityTestRule.launchActivity(new Intent());
        countryView = countryActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    private void stubbing(){
        doNothing().when(countryPresenter).onCreate();
        doNothing().when(countryPresenter).onDestroy();
    }

    @Test
    public void showToolbarWithAppName_when_launched(){
        onView(withText(context.getResources().getString(R.string.app_name)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showToolbarWithCountryName_when_loaded(){
        countryActivityActivityTestRule
                .getActivity()
                .runOnUiThread(() -> countryView.displayCountry(COUNTRY));

        onView(withText(COUNTRY_TITLE))
                .check(matches(isDisplayed()));
    }

    @Test
    public void showCities_when_loaded(){
        final City city2 = new City();
        final List<City> cities = new ArrayList<>(Arrays.asList(CITY, city2));

        final Country country = Mockito.mock(Country.class);
        when(country.getCities()).thenReturn(cities);

        countryActivityActivityTestRule
                .getActivity()
                .runOnUiThread(() -> countryView.displayCountry(country));
        // check count
        onView(withId(R.id.cities_list_recyclerview))
                .check(hasItemsCount(2));
        // check item display correctly
        onView(withId(R.id.cities_list_recyclerview))
                .check(hasHolderItemAtPosition(0, withCity(CITY)));
    }

    @Test
    public void showAvailableNetworkSnackbar_when_networkAvailable() throws Exception{
        countryView.showSnackNetworkAvailabilityMessage(true);
        onView(withText(R.string.message_network_available))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class))
                        , isCompletelyDisplayed()
                )));
    }

    @Test
    public void showUnavailableNetworkSnackbar_when_networkUnavailable() throws Exception{
        countryView.showSnackNetworkAvailabilityMessage(false);
        onView(withText(R.string.message_network_unavailable))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class))
                        , isCompletelyDisplayed()
                )));
    }

    @Test
    public void showRefreshingView_when_pullDownSwipeRefreshLayout(){
        doNothing().when(countryPresenter).getCountryInfo();
        onView(withId(R.id.cities_list_refreshlayout))
                .perform(swipeDown())
                .check(matches(isRefreshing()));
    }

    @Test
    public void showErrorSnackBar_when_onError(){
        countryActivityActivityTestRule.getActivity().runOnUiThread(() -> countryActivityActivityTestRule.getActivity().onError(ERROR_MSG));
        onView(withText(ERROR_MSG))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class))
                        , isCompletelyDisplayed()
                )));
    }

    @Test
    public void showCityToast_when_itemClick(){
        countryActivityActivityTestRule.getActivity().runOnUiThread(() -> countryActivityActivityTestRule.getActivity().displayItemToast(CITY));
        onView(withText(context.getString(R.string.toast_city_title, CITY_TITLE)))
                .inRoot(withToast())
                .check(matches(isDisplayed()));
    }

    @Test
    public void hideRefreshingView_when_pullDownSwipeRefreshLayoutThenDisplayCountry(){
        doNothing().when(countryPresenter).getCountryInfo();

        onView(withId(R.id.cities_list_refreshlayout))
                .perform(swipeDown())
                .check(matches(isRefreshing()));

        countryActivityActivityTestRule.getActivity().runOnUiThread(() -> countryView.displayCountry(COUNTRY));

        onView(withId(R.id.cities_list_refreshlayout))
                .check(matches(not(isRefreshing())));
    }

    @Test
    public void callGetCountryInfo_when_pullDownSwipeRefreshLayout(){
        onView(withId(R.id.cities_list_refreshlayout)).perform(swipeDown());
        verify(countryPresenter, times(1)).getCountryInfo();
    }

    @Test
    public void nonNullRootView_when_getView(){
        Assert.assertNotNull(countryView.getView());
    }

}
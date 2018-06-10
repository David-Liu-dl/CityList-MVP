package com.fancylab.citylistdemo.ui.splash.core;

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;

import com.fancylab.citylistdemo.R;
import com.fancylab.citylistdemo.application.InjectionHelper;
import com.fancylab.citylistdemo.application.TestAppApplication;
import com.fancylab.citylistdemo.application.dagger.TestAppComponent;
import com.fancylab.citylistdemo.base.C;
import com.fancylab.citylistdemo.ui.cities.core.CountryActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashActivity;
import com.fancylab.citylistdemo.ui.splash.core.SplashContract;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashContextModule;
import com.fancylab.citylistdemo.ui.splash.dagger.SplashModule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.Serializable;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by David Liu on 7/6/18.
 * lyhmelbourne@gmail.com
 */

@RunWith(MockitoJUnitRunner.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> splashActivityActivityTestRule = new ActivityTestRule<>(SplashActivity.class, true, false);
    @Rule
    public GrantPermissionRule internetPermissionRule = GrantPermissionRule.grant(Manifest.permission.INTERNET);

    @Inject
    InjectionHelper injectionHelper;

    @Mock
    private SplashContract.SplashPresenter splashPresenter;

    private Context context;

    private SplashModule splashModule;
    private SplashContextModule splashContextModule;
    private SplashContract.SplashView splashView;

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getApplicationContext();
        TestAppApplication app = (TestAppApplication) context;
        TestAppComponent component = (TestAppComponent) app.getAppComponent();
        component.inject(this);

        Intents.init();
        Mockito.reset(injectionHelper, splashPresenter);

        stubbing();
        splashActivityActivityTestRule.launchActivity(new Intent());
        splashView = splashActivityActivityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

    private void stubbing(){
        when(injectionHelper.getSplashModule(any())).thenAnswer(invocation -> {
            // create mock Module with real view
            splashModule = spy(new SplashModule(invocation.getArgument(0)));
            when(splashModule.providePresenter(any(), any(), any())).thenReturn(splashPresenter);
            return splashModule;
        });

        when(injectionHelper.getSplashContextModule(any())).thenAnswer(invocation -> {
            // create mock Module with real view
            splashContextModule = spy(new SplashContextModule(invocation.getArgument(0)));
            return splashContextModule;
        });

        doNothing().when(splashPresenter).onCreate();
        doNothing().when(splashPresenter).onDestroy();
    }

    @Test
    public void showWelcomeMessage_when_launched(){
        splashView.showSnackNetworkAvailabilityMessage(true);
        onView(withText(R.string.splash_welcome_message))
                .check(matches(isDisplayed()));
    }

    @Test
    public void nonNullRootView_when_getView(){
        Assert.assertNotNull(splashView.getView());
    }

    @Test
    public void showAvailableNetworkSnackbar_when_networkAvailable() throws Exception{
        splashView.showSnackNetworkAvailabilityMessage(true);
        onView(withText(R.string.message_network_available))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class))
                        , isCompletelyDisplayed()
                )));
    }

    @Test
    public void showUnavailableNetworkSnackbar_when_networkUnavailable() throws Exception{
        splashView.showSnackNetworkAvailabilityMessage(false);
        onView(withText(R.string.message_network_unavailable))
                .check(matches(allOf(
                        isDescendantOfA(isAssignableFrom(Snackbar.SnackbarLayout.class))
                        , isCompletelyDisplayed()
                )));
    }

    @Test(expected = NoMatchingViewException.class)
    public void dismissSnackbar_when_afterDuration() throws Exception{
        splashView.showSnackNetworkAvailabilityMessage(anyBoolean());

        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));

        Thread.sleep(context.getResources().getInteger(R.integer.duration_snackbar));

        onView(withId(android.support.design.R.id.snackbar_text))
                .check(matches(isDisplayed()));
    }

    @Test
    public void intent2CountryActivity_when_intentToCountryActivity(){
        final Serializable data = Mockito.mock(Serializable.class);

        intending(hasComponent(CountryActivity.class.getName()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

        splashView.intentToCountryActivity(data);

        intended(allOf(hasComponent(CountryActivity.class.getName()), hasExtra(C.data.DATA_COUNTRY, data)));
    }
}
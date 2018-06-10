package com.fancylab.citylistdemo;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;


/**
 * Created by David Liu on 10/6/18.
 * lyhmelbourne@gmail.com
 */

public class MatcherUtil {

    public static Matcher<View> isRefreshing(){
        return new BoundedMatcher<View, SwipeRefreshLayout>(SwipeRefreshLayout.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("SwipeRefreshLayout with indicator");
            }

            @Override
            protected boolean matchesSafely(SwipeRefreshLayout item) {
                return item.isRefreshing();
            }
        };
    }
}

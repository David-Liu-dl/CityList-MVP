package com.fancylab.citylistdemo;

import android.os.IBinder;
import android.support.test.espresso.Root;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancylab.citylistdemo.models.City;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


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

    public static TypeSafeMatcher<Root> withToast(){
        return new TypeSafeMatcher<Root>() {
            @Override
            protected boolean matchesSafely(Root root) {
                int type = root.getWindowLayoutParams().get().type;
                if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                    IBinder windowToken = root.getDecorView().getWindowToken();
                    IBinder appToken = root.getDecorView().getApplicationWindowToken();
                    if (windowToken == appToken) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(" is a toast");
            }
        };
    }

    public static TypeSafeMatcher<RecyclerView.ViewHolder> withCity(City city){
        return new TypeSafeMatcher<RecyclerView.ViewHolder>() {
            @Override
            protected boolean matchesSafely(RecyclerView.ViewHolder item) {
                final View itemView = item.itemView;

                TextView cityTitleTv = itemView.findViewById(R.id.city_title);
                ImageView cityImageIv = itemView.findViewById(R.id.city_image);
                TextView cityDescriptionTv = itemView.findViewById(R.id.city_description);

                return TextUtils.equals(cityTitleTv.getText(), city.getTitle())
                        && TextUtils.equals(cityDescriptionTv.getText(), city.getDescription())
                        && (cityImageIv.getDrawable() != null);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with title: " + city.getTitle()
                        + " imageHref: "
                        + city.getImageHref()
                        + " description: " + city.getDescription());
            }
        };
    }
}

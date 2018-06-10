package com.fancylab.citylistdemo;

import android.support.test.espresso.ViewAssertion;
import android.support.v7.widget.RecyclerView;

import org.hamcrest.Matcher;
import org.junit.Assert;

/**
 * Created by David Liu on 10/6/18.
 * lyhmelbourne@gmail.com
 */

public class ViewAssertionsUtil {

    public static ViewAssertion hasItemsCount(final int count) {
        return (view, e) -> {
            if (!(view instanceof RecyclerView)) {
                throw e;
            }
            RecyclerView rv = (RecyclerView) view;
            Assert.assertEquals(rv.getAdapter().getItemCount(), count);
        };
    }

    public static ViewAssertion hasHolderItemAtPosition(final int index,
                                                        final Matcher<RecyclerView.ViewHolder> viewHolderMatcher) {
        return (view, e) -> {
            if (!(view instanceof RecyclerView)) {
                throw e;
            }
            RecyclerView rv = (RecyclerView) view;
            Assert.assertThat(rv.findViewHolderForAdapterPosition(index), viewHolderMatcher);
        };
    }

}

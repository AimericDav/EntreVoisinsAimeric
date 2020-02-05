package com.openclassrooms.entrevoisins.ui.neighbour_list;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FavoriteTest {

    private ListNeighbourActivity mActivity;
    private NeighbourApiService apiService;
    private ListNeighbourPagerAdapter listNeighbourAdapter;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityTestRule = new ActivityTestRule<>(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());
        apiService = DI.getNewInstanceApiService();
        assertThat(apiService, notNullValue());
    }

    @Test
    public void neighbourListFavorite_isEmpty() {
        onView(allOf(withContentDescription("Favorites"),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.container), isDisplayed())).perform(swipeLeft());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(0));
    }
    @Test
    public void neighbourListFavorite_addNeighbourInFavorite(){
        apiService.getNeighbours().get(0).setFavorite(true);
        apiService.getNeighbours().get(1).setFavorite(true);
        onView(allOf(withContentDescription("Favorites"),isDisplayed())).perform(click());
        onView(allOf(withId(R.id.container), isDisplayed())).perform(swipeLeft());
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).check(withItemCount(2));
    }
}



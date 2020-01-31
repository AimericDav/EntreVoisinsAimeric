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
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ProfileActivityTest {

    private ListNeighbourActivity mActivity;
    private NeighbourApiService apiService;

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
    public void neighbourProfileActivity_nameIsDisplayed() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.name_neighbour_picture))
                .check(matches(withText(apiService.getNeighbours().get(0).getName())));
    }

    @Test
    public void neighbourProfileActivity_activityIsDisplayed() {
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.profile_activity))
                .check(matches(isDisplayed()));
    }
}

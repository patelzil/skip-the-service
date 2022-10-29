package com.group1.servicesapp.UI;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.group1.servicesapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReviewAcceptanceTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void LeaveReviewAcceptanceTest(){
        onView(withId(R.id.username)).perform(typeText("user"));
        onView(withId(R.id.password)).perform(typeText("123"));
        onView(withId(R.id.createUserButton)).perform(click());

        // click a category - Snow Removal
        onView(withTagValue(is("Snow Removal"))).perform(click());

        // click a service - Snow Man 1
        onView(withTagValue(is("Snow Man1 testMail.ca"))).perform(click());

        // click check review button
        onView(withId(R.id.leaveReview)).perform(click());

        // write review
        onView(withId(R.id.reportMessage)).perform(typeText("I don't like this service"));

        // click rating bar
        onView(withId(R.id.ratingBar)).perform(click());

        Espresso.closeSoftKeyboard();

        // click submit
        onView(withId(R.id.submitButton)).perform(click());

    }

    @Test
    public void CheckReviewAcceptanceTest(){
        onView(withId(R.id.username)).perform(typeText("user"));
        onView(withId(R.id.password)).perform(typeText("123"));
        onView(withId(R.id.createUserButton)).perform(click());

        // click a category - Snow Removal
        onView(withTagValue(is("Snow Removal"))).perform(click());

        // click a service - Snow Man 1
        onView(withTagValue(is("Snow Man1 testMail.ca"))).perform(click());

        // click check review button
        onView(withId(R.id.checkReview)).perform(click());
    }
}

package com.group1.servicesapp.UI;


import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.group1.servicesapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateAccountAcceptanceTests {

    @Rule
    public ActivityScenarioRule<Login> mActivityTestRule = new ActivityScenarioRule<Login>(Login.class);

    @Test
    public void createAccountAcceptanceTests() {
        // create an account first
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.createUserButton)).perform(click());

        onView(withId(R.id.userAccountEmail)).perform(typeText("test@123.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.userAccountUsername)).perform(typeText("test"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.userAccountPassword)).perform(typeText("123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.confirmPass)).perform(typeText("123"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.userAccountAddress)).perform(typeText("test street"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.createAccountButton)).perform(click());
        Espresso.closeSoftKeyboard();

        // click on the view profile button
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.nameContent)).check(matches(withText("test")));
        onView(withId(R.id.emailContent)).check(matches(withText("test@123.com")));
        onView(withId(R.id.addressContent)).check(matches(withText("test street")));

    }
}

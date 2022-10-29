package com.group1.servicesapp.UI;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.group1.servicesapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditBusinessTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);


    @Test
    public void editBusinessProfile(){
        // create a business account first
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.createBusinessButton)).perform(click());

        onView(withId(R.id.newBusinessName)).perform(typeText("Test Once"));
        onView(withId(R.id.postalAddress)).perform(typeText("66 Chancellors Cir"));
        onView(withId(R.id.emailAddress)).perform(typeText("test@service.com"));
        onView(withId(R.id.password)).perform(typeText("test12"));
        closeSoftKeyboard();
        onView(withId(R.id.confirmPassword)).perform(typeText("test12"));
        closeSoftKeyboard();
        onView(withId(R.id.registerButton)).perform(click());
        closeSoftKeyboard();

        // click on the view profile button
        onView(withId(R.id.button)).perform(click());

        // now click on the edit button to edit information
        onView(withId(R.id.edit)).perform(click());

        // edit the information
        onView(withId(R.id.newBusEmail)).perform(clearText());
        onView(withId(R.id.newBusEmail)).perform(typeText("test@info.com"));
        closeSoftKeyboard();
        onView(withId(R.id.newBusAddress)).perform(clearText());
        onView(withId(R.id.newBusAddress)).perform(typeText("66 Chancellors Cir., Winnipeg"));
        closeSoftKeyboard();

        // press submit button
        onView(withId(R.id.submitEdit)).perform(click());

        // go back and confirm the changes made
        onView(withId(R.id.busEmailContent)).check(matches(withText("test@info.com")));
        onView(withId(R.id.busAddressContent)).check(matches(withText("66 Chancellors Cir., Winnipeg")));

    }

}

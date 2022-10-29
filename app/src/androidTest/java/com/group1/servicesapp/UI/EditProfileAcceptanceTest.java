package com.group1.servicesapp.UI;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group1.servicesapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditProfileAcceptanceTest {

    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new ActivityTestRule<>(Login.class);

    @Test
    public void editProfileAcceptanceTest() {
        ViewInteraction button = onView(
                allOf(withId(R.id.registerButton), withText("No account? Register"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.createUserButton), withText("Create User Account"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.userAccountEmail),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        editText.perform(replaceText("test@123.com"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.userAccountUsername),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()));
        editText2.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.userAccountPassword),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        editText3.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.confirmPass),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        editText4.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.userAccountAddress),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        editText5.perform(replaceText("test street"), closeSoftKeyboard());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.createAccountButton), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                8),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.button), withText("View Profile"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                8),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.edit), withText("EDIT"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        2),
                                10),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.newEmail), withText("test@123.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                4),
                        isDisplayed()));
        editText6.perform(replaceText("testChange@123.com"));

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.newEmail), withText("testChange@123.com"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                4),
                        isDisplayed()));
        editText7.perform(closeSoftKeyboard());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.newAddr), withText("test street"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                6),
                        isDisplayed()));
        editText8.perform(replaceText("test street change"));

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.newAddr), withText("test street change"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                6),
                        isDisplayed()));
        editText9.perform(closeSoftKeyboard());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.submit), withText("SUBMIT"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        1),
                                7),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.emailContent), withText("testChange@123.com"),
                        withParent(withParent(withId(R.id.relativeLayout))),
                        isDisplayed()));
        textView.check(matches(withText("testChange@123.com")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.addressContent), withText("test street change"),
                        withParent(withParent(withId(R.id.relativeLayout))),
                        isDisplayed()));
        textView2.check(matches(withText("test street change")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}

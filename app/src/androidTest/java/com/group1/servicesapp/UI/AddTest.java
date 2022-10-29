package com.group1.servicesapp.UI;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.group1.servicesapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddTest {

    @Rule
    public ActivityTestRule<Login> mActivityTestRule = new ActivityTestRule<>(Login.class);

    @Test
    public void addTest() {
        ViewInteraction editText = onView(
                allOf(withId(R.id.username),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        editText.perform(replaceText("BusinessUser"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.password),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                4),
                        isDisplayed()));
        editText2.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction button = onView(
                allOf(withId(R.id.createUserButton), withText("Sign In"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.button), withText("View Profile"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                8),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.newService), withText("New Service"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativeLayout),
                                        2),
                                1),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.newServiceName),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                5),
                        isDisplayed()));
        editText3.perform(replaceText("New service"), closeSoftKeyboard());

        ViewInteraction spinner = onView(
                allOf(withId(R.id.newServiceCategory),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                6),
                        isDisplayed()));
        spinner.perform(click());

        DataInteraction textView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(6);
        textView.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.newServicePrice),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                7),
                        isDisplayed()));
        editText4.perform(replaceText("10.00"), closeSoftKeyboard());

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.newServiceDescription),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                8),
                        isDisplayed()));
        editText5.perform(replaceText("description"), closeSoftKeyboard());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.newServiceLocation),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                9),
                        isDisplayed()));
        editText6.perform(replaceText("Winnipeg"), closeSoftKeyboard());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.newServiceLocation), withText("Winnipeg"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                9),
                        isDisplayed()));
        editText7.perform(pressImeActionButton());
        Espresso.closeSoftKeyboard();

        ViewInteraction button4 = onView(
                allOf(withId(R.id.addServiceButton), withText("New Service"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.label), withText("New service"),
                        withParent(allOf(withId(R.id.services),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("New service")));

        DataInteraction textView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.services),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                13)))
                .atPosition(5);
        textView3.perform(click());

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.newServiceName),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                9),
                        isDisplayed()));
        editText8.perform(replaceText("New service 2"), closeSoftKeyboard());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.newServicePrice),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                11),
                        isDisplayed()));
        editText9.perform(replaceText("10.0"), closeSoftKeyboard());

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.newServiceDescription),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                12),
                        isDisplayed()));
        editText10.perform(replaceText("description"), closeSoftKeyboard());

        ViewInteraction editText11 = onView(
                allOf(withId(R.id.newServiceLocation),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                13),
                        isDisplayed()));
        editText11.perform(replaceText("Winnipeg"), closeSoftKeyboard());

        ViewInteraction editText12 = onView(
                allOf(withId(R.id.newServiceLocation), withText("Winnipeg"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                13),
                        isDisplayed()));
        editText12.perform(pressImeActionButton());
        Espresso.closeSoftKeyboard();

        ViewInteraction button5 = onView(
                allOf(withId(R.id.registerButton), withText("Submit Changes"),
                        childAtPosition(
                                allOf(withId(R.id.relative),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                0)),
                                6),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.label), withText("New service 2"),
                        withParent(allOf(withId(R.id.services),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("New service 2")));
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

package com.group1.servicesapp.UI;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.group1.servicesapp.R;
import com.group1.servicesapp.logic.IOrderLogic;
import com.group1.servicesapp.logic.OrderLogic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserTest {
    @Rule
    public ActivityScenarioRule<Login> activityRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void editBusiness(){
        // log in as a user
        onView(withId(R.id.username)).perform(typeText("test user"));
        onView(withId(R.id.password)).perform(typeText("123"));
        onView(withId(R.id.createUserButton)).perform(click());

        // view profile
        onView(withId(R.id.button)).perform(click());

        // view orders
        String id = addFakeOrder();
        onView(withId(R.id.viewOrders)).perform(click());

        // edit order description
        onView(withTagValue(is(id))).perform(click());
        onView(withId(R.id.descriptionContent)).check(matches(withText("description")));
        onView(withId(R.id.edit)).perform(click());
        onView(withId(R.id.descriptionContent)).perform(typeText("This is a new description"));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.descriptionContent)).check(matches(withText("This is a new description")));

        // delete order
        onView(withId(R.id.delete)).perform(click());
        onView(withText("delete")).perform(click());

        // back to profile page
        onView(withId(R.id.back)).perform(click());
        onView(withId(R.id.emailContent)).check(matches(withText("test@user.com")));
        onView(withId(R.id.addressContent)).check(matches(withText("123 Test Ave.")));

        // edit profile
        onView(withId(R.id.edit)).perform(click());
        onView(withId(R.id.newEmail)).perform(clearText());
        onView(withId(R.id.newEmail)).perform(typeText("new@user.com"));
        onView(withId(R.id.newAddr)).perform(clearText());
        onView(withId(R.id.newAddr)).perform(typeText("456 Test Ave."));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.emailContent)).check(matches(withText("new@user.com")));
        onView(withId(R.id.addressContent)).check(matches(withText("456 Test Ave.")));

        // change password
        onView(withId(R.id.changePassword)).perform(click());
        onView(withId(R.id.oldPass)).perform(typeText("123"));
        onView(withId(R.id.newPass)).perform(typeText("1234"));
        onView(withId(R.id.confirmPass)).perform(typeText("1234"));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());

        // log out and log in with new password
        onView(withId(R.id.done)).perform(click());
        onView(withId(R.id.logout_button)).perform(click());
        onView(withId(R.id.username)).perform(typeText("test user"));
        onView(withId(R.id.password)).perform(typeText("1234"));
        onView(withId(R.id.createUserButton)).perform(click());

        // reset password and profile info
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.changePassword)).perform(click());
        onView(withId(R.id.oldPass)).perform(typeText("1234"));
        onView(withId(R.id.newPass)).perform(typeText("123"));
        onView(withId(R.id.confirmPass)).perform(typeText("123"));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.edit)).perform(click());
        onView(withId(R.id.newEmail)).perform(clearText());
        onView(withId(R.id.newEmail)).perform(typeText("test@user.com"));
        onView(withId(R.id.newAddr)).perform(clearText());
        onView(withId(R.id.newAddr)).perform(typeText("123 Test Ave."));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.emailContent)).check(matches(withText("test@user.com")));
        onView(withId(R.id.addressContent)).check(matches(withText("123 Test Ave.")));
    }

    public String addFakeOrder(){
        long longStamp = System.currentTimeMillis()/1000;
        String stamp = String.valueOf(longStamp);
        IOrderLogic orderLogic = new OrderLogic(getApplicationContext());
        orderLogic.addOrder(stamp,"service","provider","test user","schedule","description");
        return stamp;
    }
}

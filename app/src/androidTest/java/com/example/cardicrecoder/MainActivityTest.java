package com.example.cardicrecoder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void test1(){
        onView(withText("Cardiac Recorder")).check(matches(isDisplayed()));
    }


    @Test
    public void test2(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.fab_main)).perform(click());
        Espresso.onView(withId(R.id.hbm_i)).perform(ViewActions.typeText("80"));
        Espresso.onView(withId(R.id.systolic_pressure_i)).perform(ViewActions.typeText("135"));
        Espresso.onView(withId(R.id.diastolic_pressure_i)).perform(ViewActions.typeText("80"));

        Espresso.pressBack(); //Back button
        Espresso. onView(withId(R.id.comment_i)).perform(ViewActions.typeText("Health is good"));
        Espresso.pressBack(); //Back button
        Espresso.onView(withId(R.id.add)).perform(click());
        Espresso.onView(withId(R.id.recylerview)).check(matches(isDisplayed()));

        SystemClock.sleep(5000);
//        Espresso.onView(withId(R.id.fab_main)).perform(click());
//        Espresso.onView(withId(R.id.hbm_i)).perform(ViewActions.typeText("90"));
//        Espresso.onView(withId(R.id.systolic_pressure_i)).perform(ViewActions.typeText("120"));
//        Espresso.onView(withId(R.id.diastolic_pressure_i)).perform(ViewActions.typeText("90"));
//
//        Espresso.pressBack(); //Back button
//        Espresso. onView(withId(R.id.comment_i)).perform(ViewActions.typeText("Health is good"));
//        Espresso.pressBack(); //Back button
//        SystemClock.sleep(2000);
//        Espresso.onView(withId(R.id.add)).perform(click());
//        Espresso.onView(withId(R.id.recylerview)).check(matches(isDisplayed()));
//        SystemClock.sleep(5000);



    }


    @Test
    public void test3() {
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.recylerview)).perform(click());
        SystemClock.sleep(5000);
    }

    @Test
    public void test4(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.recylerview)).perform(longClick());
        SystemClock.sleep(5000);
        Espresso.onView(withText("Edit")).perform(click());
        Espresso.onView(withId(R.id.hbm_i)).perform(clearText()).perform(ViewActions.typeText("70"));
        Espresso.onView(withId(R.id.systolic_pressure_i)).perform(clearText()).perform(ViewActions.typeText("120"));
        Espresso.onView(withId(R.id.diastolic_pressure_i)).perform(clearText()).perform(ViewActions.typeText("78"));
        Espresso.pressBack(); //Back button
        Espresso. onView(withId(R.id.comment_i)).perform(clearText()).perform(ViewActions.typeText("After Sleep"));
        Espresso.pressBack(); //Back button
        SystemClock.sleep(2000);
        Espresso.onView(withId(R.id.add)).perform(click());
        SystemClock.sleep(5000);
    }
    @Test
    public void test5(){
        SystemClock.sleep(5000);
        Espresso.onView(withId(R.id.recylerview)).perform(longClick());
        SystemClock.sleep(5000);
        Espresso.onView(withText("Delete")).perform(click());
        SystemClock.sleep(5000);
    }
}
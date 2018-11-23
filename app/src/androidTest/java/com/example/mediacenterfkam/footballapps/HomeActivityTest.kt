package com.example.mediacenterfkam.footballapps

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.example.mediacenterfkam.footballapps.R.id.*
import android.support.v7.widget.RecyclerView
import com.example.mediacenterfkam.footballapps.home.HomeActivity
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)



    @Test
    fun buttonNavigationTest(){
        Espresso.onView(ViewMatchers.withId(bottomNavigation)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(navigation_favorite)).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(navigation_teams)).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(navigation_macthes)).perform(ViewActions.click())

    }



    @Test
    fun actionAddRemoveFavorite(){
        //Action Add
        Espresso.onView(ViewMatchers.withId(homeSpinner)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(homeSpinner)).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withText("German Bundesliga")).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(rv_current)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rv_current)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        Espresso.onView(ViewMatchers.withId(rv_current))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, ViewActions.click()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.pressBack()

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(viewpagerMatch)).perform(ViewActions.swipeLeft())
        Espresso.onView(ViewMatchers.withId(rv_next)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(rv_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        Espresso.onView(ViewMatchers.withId(rv_next))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, ViewActions.click()))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        Espresso.pressBack()


        //Aciton Remove
        Espresso.onView(ViewMatchers.withId(navigation_favorite)).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(swipeFavorite)).perform(ViewActions.swipeDown())
        Espresso.onView(ViewMatchers.withId(rvfavorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(rvfavorite))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        Thread.sleep(2000)

        Espresso.onView(ViewMatchers.withId(add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText("CANCEL")).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())

        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText("YES")).perform(ViewActions.click())

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(rvfavorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(swipeFavorite)).perform(ViewActions.swipeDown())

    }



}
package com.example.fauzy.footballmatchschedule.HomeActivity

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.fauzy.footballmatchschedule.R.id.add_to_favorite
import com.example.fauzy.footballmatchschedule.R.id.last_match_list
import com.example.fauzy.footballmatchschedule.ui.activities.MainActivity
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FootballInstrumentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Test
    fun testRecyclerViewBehaviour() {
        Thread.sleep(2000)
        onView(allOf(withId(last_match_list), hasFocus())).check(matches(isDisplayed()))
        onView(
            allOf(
                withId(last_match_list),
                hasFocus()
            )
        ).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(
            allOf(
                withId(last_match_list),
                hasFocus()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Application is still loading the data. Please wait..")).inRoot(
            withDecorView(
                not(
                    `is`(
                        activityRule.activity.window.decorView
                    )
                )
            )
        )
            .check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Added to favorite")).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
        pressBack()
        onView(
            allOf(
                withId(last_match_list),
                hasFocus()
            )
        ).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        Thread.sleep(1500)
        onView(withId(add_to_favorite)).perform(click())
        onView(withText("Removed from favorite")).inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))

    }
}

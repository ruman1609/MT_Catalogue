package com.rudyrachman16.mtcatalogue.views.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.rudyrachman16.mtcatalogue.R
import com.rudyrachman16.mtcatalogue.data.Repositories
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    private val movieList = Repositories().getMovies()
    private val tvShowList = Repositories().getTvShow()

    @get:Rule
    val rule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun testLoadMovies() {
        onView(withId(R.id.tabRecycler)).check(matches(isDisplayed()))
        onView(withId(R.id.tabRecycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieList.size - 1
            )
        )
    }

    @Test
    fun testLoadTvShow() {
        onView(withText(R.string.tv_shows)).perform(click())
        onView(withId(R.id.tabRecycler)).check(matches(isDisplayed()))
        onView(withId(R.id.tabRecycler)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                tvShowList.size - 1
            )
        )
    }

    @Test
    fun testLoadDetail() {
        onView(withId(R.id.tabRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.detType)).check(matches(isDisplayed()))
        onView(withId(R.id.detDesc)).perform(swipeUp()).check(matches(isDisplayed()))
    }
}
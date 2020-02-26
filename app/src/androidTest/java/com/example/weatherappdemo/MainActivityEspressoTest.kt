package com.example.weatherappdemo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.weatherappdemo.utils.InternetCheckUtils
import com.example.weatherappdemo.views.MainActivity
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test


class MainActivityEspressoTest {

    @get:Rule
    val mActivityRule = ActivityTestRule(MainActivity::class.java)

    /*
    * Check for retry view based on Internet connection
    *
    * */
    @Test
    fun checkIfRetryViewAppear() {
        if (!InternetCheckUtils.isNetworkAvailable(mActivityRule.activity)) {
            onView(withId(R.id.tvRetryMessage)).check(matches(isDisplayed()))
            onView(withText("Retry")).check(matches(isDisplayed()))
            onView(withId(R.id.clCurrentWeather)).check(matches(withEffectiveVisibility(Visibility.GONE)))
            onView(withId(R.id.tvCurrentLocation)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        }
    }


    /*
    * Check for No Internet Toast
    *
    * */
    @Test
    fun checkIfNoInternetToastAppeared() {
        if (!InternetCheckUtils.isNetworkAvailable(mActivityRule.activity)) {
            onView(withText(startsWith("No"))).inRoot(
                withDecorView(
                    not(`is`(mActivityRule.getActivity().getWindow().getDecorView()))
                )
            ).check(matches(isDisplayed()))
        }
    }

    /*
    * Check for Current weather view if internet is available
    *
    * */

    @Test
    fun checkIfCurrentWeatherViewAppear() {
        if (InternetCheckUtils.isNetworkAvailable(mActivityRule.activity)) {
            Thread.sleep(4000L)
            onView(withId(R.id.clCurrentWeather)).check(matches(isDisplayed()))
        }
    }

}
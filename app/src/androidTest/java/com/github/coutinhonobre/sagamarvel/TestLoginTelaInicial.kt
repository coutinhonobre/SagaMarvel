package com.github.coutinhonobre.sagamarvel

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.github.coutinhonobre.sagamarvel.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class TestLoginTelaInicial {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checarComponentesEmTelaTest(){
        onView(withId(R.id.recyclerViewMainMovies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clicarItemTelaTest(){
        onView(withId(R.id.recyclerViewMainMovies)).perform(swipeUp(), click())
        Thread.sleep(2000)
    }

    @Test
    fun clicarItemTelaParaDetailTest(){
        onView(withId(R.id.recyclerViewMainMovies)).perform(swipeUp(), click())
        Thread.sleep(3000)
        onView(withId(R.id.recyclerViewMainMovies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imageMovieDetailImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imageButtonMovieDetailLike))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.ratingBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.recyclerMovieDetailDados))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clicarItemTelaParaDetailLikeTest(){
        onView(withId(R.id.recyclerViewMainMovies)).perform(swipeUp(), click())
        Thread.sleep(3000)
        onView(withId(R.id.recyclerViewMainMovies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        onView(withId(R.id.imageButtonMovieDetailLike)).perform(click())
    }

    @Test
    fun clicarItemTelaParaDetailStarTest(){
        onView(withId(R.id.recyclerViewMainMovies)).perform(swipeUp(), click())
        Thread.sleep(3000)
        onView(withId(R.id.recyclerViewMainMovies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.ratingBar)).perform(click())
    }

    @Test
    fun clicarItemTelaParaDetailTrailerTest(){
        onView(withId(R.id.recyclerViewMainMovies)).perform(swipeUp(), click())
        Thread.sleep(3000)
        onView(withId(R.id.recyclerViewMainMovies)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        onView(withId(R.id.menu_movie_maior)).perform(click())
    }


}
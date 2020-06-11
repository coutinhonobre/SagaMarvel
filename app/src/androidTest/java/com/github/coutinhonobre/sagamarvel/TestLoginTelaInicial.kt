package com.github.coutinhonobre.sagamarvel

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
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
        Espresso.onView(withId(R.id.recyclerViewMainMovies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{

    @Before
    fun setUpCourse(){
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun validateUITestHomeActivityCourse(){
        Intents.init()
        onView(withId(R.id.action_add)).perform(click())
        Intents.intended(hasComponent(AddCourseActivity::class.java.name))
    }
}
package com.zaki.sosmedapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.zaki.sosmedapp.R
import com.zaki.sosmedapp.di.RepositoryModule
import com.zaki.sosmedapp.helper.RecyclerViewItemCountAssertion
import com.zaki.sosmedapp.helper.RecyclerViewMatcherUtils.atPosition
import com.zaki.sosmedapp.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@ExperimentalCoroutinesApi
@UninstallModules(RepositoryModule::class)
@HiltAndroidTest
class PostsFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
        launchFragmentInHiltContainer<PostsFragment>()
    }

    @Test
    fun testPostList() {
        onView(withId(R.id.rv_posts)).check(RecyclerViewItemCountAssertion(2))
        onView(withId(R.id.rv_posts))
            .check(matches(atPosition(0, hasDescendant(withText("Tutorial Android Dev")))))
        onView(withId(R.id.rv_posts))
            .check(matches(atPosition(0, hasDescendant(withText("Budi Santoso")))))
        onView(withId(R.id.rv_posts))
            .check(matches(atPosition(1, hasDescendant(withText("Tutorial Web Dev")))))
        onView(withId(R.id.rv_posts))
            .check(matches(atPosition(1, hasDescendant(withText("Budi Santoso")))))
    }
}
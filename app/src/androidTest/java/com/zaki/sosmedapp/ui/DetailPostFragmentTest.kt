package com.zaki.sosmedapp.ui

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.zaki.sosmedapp.R
import com.zaki.sosmedapp.di.RepositoryModule
import com.zaki.sosmedapp.helper.RecyclerViewItemCountAssertion
import com.zaki.sosmedapp.helper.RecyclerViewMatcherUtils.atPosition
import com.zaki.sosmedapp.launchFragmentInHiltContainer
import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User
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
class DetailPostFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val post = Post(
        userId = 1,
        id = 1,
        title = "Tutorial Membuat Aplikasi Android",
        body = "Lorep ipsum dolor sit amet",
        user = User(
            username = "Zaki Mukhammad"
        )
    )

    @Before
    fun setup() {
        hiltRule.inject()

        val bundle = Bundle().apply {
            putParcelable("post", post)
        }
        launchFragmentInHiltContainer<DetailPostFragment>(bundle)
    }

    @Test
    fun testPostDetail() {
        Espresso.onView(withId(R.id.tv_title)).check(matches(withText(post.title)))
        Espresso.onView(withId(R.id.tv_user_name)).check(matches(withText("by ${post.user.username}")))
        Espresso.onView(withId(R.id.tv_description)).check(matches(withText(post.body)))
        Espresso.onView(withId(R.id.rv_comments)).check(RecyclerViewItemCountAssertion(1))
        Espresso.onView(withId(R.id.rv_comments))
            .check(matches(atPosition(0, hasDescendant(withText("Bagus Sekali")))))
        Espresso.onView(withId(R.id.rv_comments))
            .check(matches(atPosition(0, hasDescendant(withText("by Budi")))))
    }
}
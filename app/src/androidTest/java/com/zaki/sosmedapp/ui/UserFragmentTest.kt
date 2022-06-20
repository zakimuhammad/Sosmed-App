package com.zaki.sosmedapp.ui

import android.os.Bundle
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
import com.zaki.sosmedapp.network.model.Address
import com.zaki.sosmedapp.network.model.Company
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
class UserFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val user = User(
        id = 1,
        name = "Budi",
        username = "Budi Santoso",
        company = Company(
            name = "Google"
        ),
        address = Address(
            street = "Jalan Patimura",
            suite = "Gedung Kelapa",
            city = "Lumajang",
            zipcode = "67371"
        ),
        email = "budi@gmail.com"
    )

    @Before
    fun setup() {
        hiltRule.inject()

        val bundle = Bundle().apply {
            putParcelable("user", user)
        }
        launchFragmentInHiltContainer<UserFragment>(bundle)
    }

    @Test
    fun testUserDetail() {
        onView(withId(R.id.tv_user_name)).check(matches(withText(user.username)))
        onView(withId(R.id.tv_email)).check(matches(withText(user.email)))
        onView(withId(R.id.tv_address)).check(matches(withText(user.address.getAddress())))
        onView(withId(R.id.tv_company)).check(matches(withText(user.company.name)))
    }

    @Test
    fun testUserAlbumsList() {
        onView(withId(R.id.rv_albums)).check(RecyclerViewItemCountAssertion(1))
        onView(withId(R.id.rv_albums))
            .check(matches(atPosition(0, hasDescendant(withText("Album Sekolah")))))
        onView(withId(R.id.rv_photos)).check(RecyclerViewItemCountAssertion(1))
        onView(withId(R.id.rv_photos))
            .check(matches(atPosition(0, hasDescendant(withText("Foto Sekolah")))))
    }
}
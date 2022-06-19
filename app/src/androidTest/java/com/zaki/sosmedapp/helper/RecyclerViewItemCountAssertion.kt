package com.zaki.sosmedapp.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.core.Is
import org.junit.Assert

class RecyclerViewItemCountAssertion(expectedCount: Int) : ViewAssertion {

    private var expectedCount: Int? = null

    init {
        this.expectedCount = expectedCount
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView: RecyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        Assert.assertNotNull(adapter)
        ViewMatchers.assertThat(adapter?.itemCount, Is.`is`(expectedCount))
    }
}
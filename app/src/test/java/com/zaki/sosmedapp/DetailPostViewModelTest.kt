package com.zaki.sosmedapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zaki.sosmedapp.network.model.Comment
import com.zaki.sosmedapp.network.repository.SosmedRepository
import com.zaki.sosmedapp.viewmodel.DetailPostViewModel
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailPostViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: DetailPostViewModel

    @MockK
    lateinit var repository: SosmedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = DetailPostViewModel(repository)
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun testGetComments() {
        val comments = listOf(
            Comment(
                postId = 1,
                id = 1,
                name = "Budi",
                body = "Bagus Sekali"
            ),
            Comment(
                postId = 1,
                id = 2,
                name = "Slamet",
                body = "Sangat Menarik"
            )
        )

        coEvery { repository.getComments(any()) } returns comments

        subject.getComments("")

        Truth.assertThat(subject.comment.value).isNull()
        Truth.assertThat(subject.comment.value?.size).isEqualTo(comments.size)
        Truth.assertThat(subject.comment.value?.get(0)).isEqualTo(comments[0])
        Truth.assertThat(subject.comment.value?.get(1)).isEqualTo(comments[1])
    }
}
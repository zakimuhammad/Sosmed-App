package com.zaki.sosmedapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zaki.sosmedapp.network.model.Address
import com.zaki.sosmedapp.network.model.Company
import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User
import com.zaki.sosmedapp.network.repository.SosmedRepository
import com.zaki.sosmedapp.viewmodel.PostViewModel
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
class PostViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: PostViewModel

    @MockK
    lateinit var repository: SosmedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun testGetPostData() {
        val users = listOf(
            User(
                id = 1,
                name = "Budi",
            ),
            User(
                id = 2,
                name = "Slamet",
            )
        )

        val posts = listOf(
            Post(
                userId = 2,
                id = 1,
                title = "Tutorial Android",
                body = "Lorep Ipsum",
            ),
            Post(
                userId = 1,
                id = 2,
                title = "Tutorial Web",
                body = "Lorep Ipsum",
            )
        )

        coEvery { repository.getUsers() } returns users
        coEvery { repository.getPosts() } returns posts

        subject = PostViewModel(repository)

        Truth.assertThat(subject.posts.value).isNull()
        Truth.assertThat(subject.posts.value?.size).isEqualTo(posts.size)
        Truth.assertThat(subject.posts.value?.get(0)?.user?.id).isEqualTo(users[1].id)
        Truth.assertThat(subject.posts.value?.get(1)?.user?.id).isEqualTo(users[0].id)
    }
}
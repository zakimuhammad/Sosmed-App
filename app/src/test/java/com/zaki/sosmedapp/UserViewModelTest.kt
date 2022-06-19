package com.zaki.sosmedapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.zaki.sosmedapp.network.model.Album
import com.zaki.sosmedapp.network.model.Photo
import com.zaki.sosmedapp.network.repository.SosmedRepository
import com.zaki.sosmedapp.viewmodel.UserViewModel
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
class UserViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var subject: UserViewModel

    @MockK
    lateinit var repository: SosmedRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        subject = UserViewModel(repository)
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }

    @Test
    fun testGetAlbums() {
        val albums = listOf(
            Album(
                userId = 2,
                id = 1,
                title = "Album Kenangan"
            ),
            Album(
                userId = 1,
                id = 1,
                title = "Album Kuliah",
            )
        )

        val photos = listOf(
            Photo(
                albumId = 1,
                id = 1,
                title = "Foto SMA"
            ),
            Photo(
                albumId = 1,
                id = 2,
                title = "Foto Kuliah"
            )
        )

        coEvery { repository.getAlbums(any()) } returns albums
        coEvery { repository.getPhotos(any()) } returns photos

        subject.getAlbums(1)

        Truth.assertThat(subject.album.value).isNull()
        Truth.assertThat(subject.album.value?.size).isEqualTo(albums.size)
        Truth.assertThat(subject.album.value?.get(0)?.photos?.size).isEqualTo(photos.size)
        Truth.assertThat(subject.album.value?.get(0)?.photos?.get(0)?.albumId).isEqualTo(photos[0].albumId)
        Truth.assertThat(subject.album.value?.get(0)?.id).isEqualTo(albums[0].id)
    }
}
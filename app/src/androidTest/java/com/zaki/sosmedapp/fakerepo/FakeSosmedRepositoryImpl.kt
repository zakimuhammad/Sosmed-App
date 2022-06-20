package com.zaki.sosmedapp.fakerepo

import com.zaki.sosmedapp.network.model.*
import com.zaki.sosmedapp.network.repository.SosmedRepository
import javax.inject.Inject

class FakeSosmedRepositoryImpl @Inject constructor(): SosmedRepository {
    override suspend fun getUsers(): List<User> {
        return listOf(
            User(
                id = 1,
                username = "Budi Santoso"
            )
        )
    }

    override suspend fun getPosts(): List<Post> {
        return listOf(
            Post(
                userId = 1,
                id = 1,
                title = "Tutorial Android Dev"
            ),
            Post(
                userId = 1,
                id = 2,
                title = "Tutorial Web Dev"
            ),
        )
    }

    override suspend fun getComments(postId: String): List<Comment> {
        return listOf(
            Comment(
                postId = 1,
                id = 1,
                name = "Budi",
                email = "budi@gmail.com",
                body = "Bagus Sekali"
            )
        )
    }

    override suspend fun getAlbums(userId: Int): List<Album> {
        return listOf(
            Album(
                userId = 1,
                id = 1,
                title = "Album Sekolah"
            )
        )
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        return listOf(
            Photo(
                albumId = 1,
                id = 1,
                title = "Foto Sekolah",
                url = "www.google.com",
                thumbnailUrl = "www.google.com"
            )
        )
    }
}
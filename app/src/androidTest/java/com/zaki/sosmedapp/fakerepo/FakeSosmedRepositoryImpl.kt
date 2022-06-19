package com.zaki.sosmedapp.fakerepo

import com.zaki.sosmedapp.network.model.*
import com.zaki.sosmedapp.network.repository.SosmedRepository
import javax.inject.Inject

class FakeSosmedRepositoryImpl @Inject constructor(): SosmedRepository {
    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(): List<Post> {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        TODO("Not yet implemented")
    }
}
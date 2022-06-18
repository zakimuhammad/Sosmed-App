package com.zaki.sosmedapp.network.repository

import com.zaki.sosmedapp.network.api.SosmedApi
import com.zaki.sosmedapp.network.model.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import javax.inject.Inject

class SosmedRepositoryImpl @Inject constructor(
    retrofit: Retrofit
): SosmedRepository {

    private val api = retrofit.create(SosmedApi::class.java)

    override suspend fun getUsers(): List<User> {
        val response = api.getUsers().awaitResponse()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getPosts(): List<Post> {
        val  response = api.getPosts().awaitResponse()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getComments(postId: String): List<Comment> {
        val response = api.getComments(postId).awaitResponse()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getAlbums(userId: Int): List<Album> {
        val response = api.getAlbums(userId).awaitResponse()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }

    override suspend fun getPhotos(albumId: Int): List<Photo> {
        val response = api.getPhotos(albumId).awaitResponse()

        return if (response.isSuccessful) {
            response.body() ?: listOf()
        } else {
            listOf()
        }
    }
}
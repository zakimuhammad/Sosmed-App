package com.zaki.sosmedapp.network.repository

import com.zaki.sosmedapp.network.model.*

interface SosmedRepository {
    suspend fun getUsers(): List<User>

    suspend fun getPosts(): List<Post>

    suspend fun getComments(postId: String): List<Comment>

    suspend fun getAlbums(userId: Int): List<Album>

    suspend fun getPhotos(albumId: Int): List<Photo>
}
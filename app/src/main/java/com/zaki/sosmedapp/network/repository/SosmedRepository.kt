package com.zaki.sosmedapp.network.repository

import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User

interface SosmedRepository {
    suspend fun getUsers(): List<User>

    suspend fun getPosts(): List<Post>
}
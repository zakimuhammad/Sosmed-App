package com.zaki.sosmedapp.network.repository

import com.zaki.sosmedapp.network.api.SosmedApi
import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User
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
}
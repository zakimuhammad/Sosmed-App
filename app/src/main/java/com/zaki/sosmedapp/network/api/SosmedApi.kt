package com.zaki.sosmedapp.network.api

import com.zaki.sosmedapp.network.model.Post
import com.zaki.sosmedapp.network.model.User
import retrofit2.Call
import retrofit2.http.GET

interface SosmedApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
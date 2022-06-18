package com.zaki.sosmedapp.network.api

import com.zaki.sosmedapp.network.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SosmedApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{idPost}/comments")
    fun getComments(@Path("idPost") idPost: String): Call<List<Comment>>

    @GET("users/{userId}/albums")
    fun getAlbums(@Path("userId") userId: Int): Call<List<Album>>

    @GET("albums/{albumId}/photos")
    fun getPhotos(@Path("albumId") albumId: Int): Call<List<Photo>>
}
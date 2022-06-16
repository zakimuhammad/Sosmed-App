package com.zaki.sosmedapp.network.model

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var user: User
)
package com.zaki.sosmedapp.network.model

data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    var photos: List<Photo>
)

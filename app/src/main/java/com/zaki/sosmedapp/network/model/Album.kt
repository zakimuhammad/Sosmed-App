package com.zaki.sosmedapp.network.model

data class Album(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    var photos: List<Photo> = listOf()
)

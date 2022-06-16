package com.zaki.sosmedapp.network.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val company: Company,
    val address: Address,
)

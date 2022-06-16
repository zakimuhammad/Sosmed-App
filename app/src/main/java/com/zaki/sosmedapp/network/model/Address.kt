package com.zaki.sosmedapp.network.model

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String
) {
    fun getAddress(): String = "$suite, $street, $city, $zipcode"
}

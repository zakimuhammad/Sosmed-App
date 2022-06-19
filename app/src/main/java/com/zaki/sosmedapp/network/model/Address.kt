package com.zaki.sosmedapp.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val street: String = "",
    val suite: String = "",
    val city: String = "",
    val zipcode: String = ""
): Parcelable {
    fun getAddress(): String = "$suite, $street, $city, $zipcode"
}

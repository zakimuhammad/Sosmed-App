package com.zaki.sosmedapp.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val company: Company = Company(),
    val address: Address = Address(),
    val email: String = ""
): Parcelable

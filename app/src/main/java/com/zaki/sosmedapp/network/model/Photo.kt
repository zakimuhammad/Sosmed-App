package com.zaki.sosmedapp.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val albumId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val url: String = "",
    val thumbnailUrl: String = "",
): Parcelable

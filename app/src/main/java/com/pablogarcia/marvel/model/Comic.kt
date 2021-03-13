package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    var id: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var pageCount: Int? = null,
    var thumbnail: Thumbnail? = null
) : Parcelable

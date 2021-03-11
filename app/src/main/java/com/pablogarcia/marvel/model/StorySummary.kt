package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StorySummary(
    var resourceURI: String?,
    var name: String?,
    var type: String?
) : Parcelable

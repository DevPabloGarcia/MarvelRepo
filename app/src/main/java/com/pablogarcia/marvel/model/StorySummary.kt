package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class StorySummary(
    var resourceURI: String?,
    var name: String?,
    var type: String?
) : Parcelable

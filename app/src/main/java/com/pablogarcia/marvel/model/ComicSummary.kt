package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicSummary(
    var resourceURI: String?,
    var name: String?
) : Parcelable

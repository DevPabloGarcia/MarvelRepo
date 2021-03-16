package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesSummary(
    var resourceURI: String?,
    var name: String?
) : Parcelable

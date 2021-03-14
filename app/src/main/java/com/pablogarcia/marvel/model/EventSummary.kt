package com.pablogarcia.marvel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventSummary(
    var resourceURI: String?,
    var name: String?
) : Parcelable
